import React from 'react';
import ReactDOM from 'react-dom';

export default class extends React.Component {
	render() {
		return (
            <div>
				<div>
        	        <span className="tag">Portlet Namespace:</span> 
					<span className="value">{this.props.portletNamespace}</span>
				</div>
				<div>
    	            <span className="tag">Context Path:</span>
					<span className="value">{this.props.contextPath}</span>
				</div>
				<div>
	                <span className="tag">Portlet Element Id:</span>
					<span className="value">{this.props.portletElementId}</span>
				</div>
				
				<div>
					<span className="tag">Configuration:</span>
					<span className="value pre">{JSON.stringify(this.props.configuration, null, 2)}</span>
				</div>
				
			</div>
		);
	}	
}