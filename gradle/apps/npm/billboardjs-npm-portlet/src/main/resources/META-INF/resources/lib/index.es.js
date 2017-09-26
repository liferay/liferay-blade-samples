import { bb, d3 } from 'billboard.js/dist/billboard';

export default function(portletNamespace) {
	const columnsData = [
		['data1', 300, 350, 300, 0, 0, 100],
		['data2', 130, 100, 140, 200, 150, 50],
	];

	const barChart = bb.generate({
		data: {
			columns: columnsData,
			type: 'bar',
		},
		bar: {
			width: {
				ratio: 0.5,
			},
		},
		bindto: `#${portletNamespace}-BarChart`,
	});

	const lineChart = bb.generate({
		data: {
			columns: columnsData,
		},
		bindto: `#${portletNamespace}-LineChart`,
	});

	const splineChart = bb.generate({
		data: {
			columns: columnsData,
			type: 'spline',
		},
		bindto: `#${portletNamespace}-SplineChart`,
	});

	const stepChart = bb.generate({
		data: {
			columns: columnsData,
			types: {
				data1: 'step',
				data2: 'area-step',
			},
		},
		bindto: `#${portletNamespace}-StepChart`,
	});

	const areaChart = bb.generate({
		data: {
			columns: columnsData,
			types: {
				data1: 'area',
				data2: 'area-spline',
			},
		},
		bindto: `#${portletNamespace}-AreaChart`,
	});

	const stackedAreaChart = bb.generate({
		data: {
			columns: columnsData,
			types: {
				data1: 'area-spline',
				data2: 'area-spline',
			},
			groups: [['data1', 'data2']],
		},
		bindto: `#${portletNamespace}-StackedAreaChart`,
	});

	const svg = d3.select(`#${portletNamespace}-D3Graph`),
		width = +svg.attr('width'),
		height = +svg.attr('height');

	d3.json('/o/billboardjs-npm-portlet/lib/data.json', (error, graphData) => {
		const color = d3.scaleOrdinal(d3.schemeCategory20);

		const simulation = d3
			.forceSimulation()
			.force('link', d3.forceLink().id(d => d.id))
			.force('charge', d3.forceManyBody())
			.force('center', d3.forceCenter(width / 2, height / 2));

		const link = svg
			.append('g')
			.attr('class', 'links')
			.selectAll('line')
			.data(graphData.links)
			.enter()
			.append('line')
			.attr('stroke-width', d => Math.sqrt(d.value));

		const node = svg
			.append('g')
			.attr('class', 'nodes')
			.selectAll('circle')
			.data(graphData.nodes)
			.enter()
			.append('circle')
			.attr('r', 5)
			.attr('fill', d => color(d.group))
			.call(
				d3
					.drag()
					.on('start', d => {
						if (!d3.event.active)
							simulation.alphaTarget(0.3).restart();
						d.fx = d.x;
						d.fy = d.y;
					})
					.on('drag', d => {
						d.fx = d3.event.x;
						d.fy = d3.event.y;
					})
					.on('end', d => {
						if (!d3.event.active) simulation.alphaTarget(0);
						d.fx = null;
						d.fy = null;
					}),
			);

		node.append('title').text(d => d.id);

		simulation.nodes(graphData.nodes).on('tick', () => {
			link
				.attr('x1', d => d.source.x)
				.attr('y1', d => d.source.y)
				.attr('x2', d => d.target.x)
				.attr('y2', d => d.target.y);

			node.attr('cx', d => d.x).attr('cy', d => d.y);
		});

		simulation.force('link').links(graphData.links);
	});
}