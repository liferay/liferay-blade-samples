/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from CloseHeader.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace CloseHeader.
 * @public
 */

goog.module('CloseHeader.incrementaldom');

var incrementalDom = goog.require('incrementaldom');
var soyIdom = goog.require('soy.idom');


/**
 * @param {Object<string, *>=} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
var $render = function(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  incrementalDom.elementOpenStart('header');
      incrementalDom.attr('class', opt_data.cssClass);
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('button');
      incrementalDom.attr('onClick', opt_data.onClick);
      incrementalDom.attr('type', 'button');
      incrementalDom.attr('class', 'close');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpen('span');
  incrementalDom.text('\u00D7');
  incrementalDom.elementClose('span');
  incrementalDom.elementClose('button');
  incrementalDom.elementOpen('h4');
  soyIdom.print(opt_data.title);
  incrementalDom.elementClose('h4');
  incrementalDom.elementClose('header');
};
exports.render = $render;
if (goog.DEBUG) {
  $render.soyTemplateName = 'CloseHeader.render';
}

exports.render.params = ["cssClass","onClick","title"];
exports.render.types = {"cssClass":"any","onClick":"any","title":"any"};
templates = exports;
return exports;

});

class CloseHeader extends Component {}
Soy.register(CloseHeader, templates);
export { CloseHeader, templates };
export default templates;
/* jshint ignore:end */
