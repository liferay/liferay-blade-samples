package blade.cli.cmds;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.commons.lang3.text.WordUtils;

import blade.cli.CreateOptions;
import blade.cli.blade;
import aQute.bnd.osgi.Jar;
import aQute.bnd.osgi.Processor;
import aQute.bnd.osgi.Resource;
import aQute.lib.io.IO;

public class CreateCommand {

	final private blade blade;
	final private CreateOptions options;
	final static private List<String> textExtensions = Arrays.asList(".bnd", ".java", ".xml");

	public CreateCommand(blade blade, CreateOptions options) throws Exception {
		this.blade = blade;
		this.options = options;

		List<String> args = options._();

		if (args.size() == 0) {
			// Default command
			printHelp();
		}
		else {
			createFromTemplate();
		}
	}

	private void createFromTemplate() throws Exception {
		File base = blade.getBase();

		String name = options._().get(0);

		File dir = options.dir();

		File workDir = null;

		if( dir != null ) {
			workDir = Processor.getFile(dir, name);
			name = workDir.getName();
			base = workDir.getParentFile();
		}
		else {
			workDir = Processor.getFile(base, name);
			name = workDir.getName();
			base = workDir.getParentFile();
		}

		workDir.mkdirs();

		InputStream in = getClass().getResourceAsStream("/templates.zip");

		if (in == null) {
			blade.error("Cannot find templates in this jar %s", "/templates.zip");
			return;
		}

		Pattern glob = Pattern.compile("[^/]+|templates/" + options.build() + "/.*|\\...+/.*");

		Map<String, String> subs = new HashMap<>();
		subs.put("templates/" + options.build() + "/" + options.type() + "/", "");
		subs.put("_name_", name.toLowerCase());
		subs.put("_NAME_", WordUtils.capitalize(name));

		copy(workDir, in, glob, true, subs);
	}

	private void copy(File workspaceDir, InputStream in, Pattern glob, boolean overwrite, Map<String, String> subs) throws Exception {
		Jar jar = new Jar("dot", in);

		try {
			for (Entry<String,Resource> e : jar.getResources().entrySet()) {
				String path = e.getKey();
				blade.trace("path %s", path);

				if (glob != null && !glob.matcher(path).matches())
					continue;

				Resource r = e.getValue();

				for (String key : subs.keySet()) {
					path = path.replaceAll(key, subs.get(key));
				}

				File dest = Processor.getFile(workspaceDir, path);

				if (overwrite || !dest.isFile() || dest.lastModified() < r.lastModified() || r.lastModified() <= 0) {

					blade.trace("copy %s to %s", path, dest);

					File dp = dest.getParentFile();
					if (!dp.exists() && !dp.mkdirs()) {
						throw new IOException("Could not create directory " + dp);
					}

					IO.copy(r.openInputStream(), dest);

					if(isTextFile(dest)) {
						process(dest,subs);
					}
				}
			}
		}
		finally {
			jar.close();
		}
	}

	private void process(File dest, Map<String, String> subs) throws Exception {
		String content = new String(IO.read(dest));
		String newContent = content;

		for (String sub : subs.keySet()) {
			newContent = newContent.replaceAll(sub, subs.get(sub));
		}

		if (!content.equals(newContent)) {
			IO.write(newContent.getBytes(), dest);
		}
	}

	private boolean isTextFile(File dest) {
		String name = dest.getName();

		return textExtensions.contains(name.substring(name.lastIndexOf("."),name.length()));
	}

	private void printHelp() throws Exception {
		Formatter f = new Formatter();
		options._command().help(f, this);
		blade.out().println(f);
		f.close();
	}
}
