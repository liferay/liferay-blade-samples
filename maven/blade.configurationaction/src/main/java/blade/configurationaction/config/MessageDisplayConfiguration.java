
package blade.configurationaction.config;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(id = "blade.configurationaction.config.MessageDisplayConfiguration")
public interface MessageDisplayConfiguration {

	@Meta.AD(required = false)
	public String fontFamily();

	@Meta.AD(required = false)
	public int fontSize();

	@Meta.AD(required = false)
	public String fontColor();
}
