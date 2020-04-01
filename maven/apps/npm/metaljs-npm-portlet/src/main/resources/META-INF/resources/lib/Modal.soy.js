/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from Modal.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace Modal.
 * @public
 */

goog.module('Modal.incrementaldom');

var incrementalDom = goog.require('incrementaldom');
var soyIdom = goog.require('soy.idom');

var $templateAlias1 = Soy.getTemplate('CloseHeader.incrementaldom', 'render');


/**
 * @param {Object<string, *>=} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
var $render = function(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'liferay-npm-example-metal-modal modal ' + (opt_data.shown ? '' : 'hide'));
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'modal-dialog');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'modal-content');
  incrementalDom.elementOpenEnd();
  $templateAlias1({cssClass: 'modal-header', onClick: opt_data.close, title: opt_data.header}, null, opt_ijData);
  incrementalDom.elementOpenStart('section');
      incrementalDom.attr('class', 'modal-body');
  incrementalDom.elementOpenEnd();
  soyIdom.print(opt_data.body);
  incrementalDom.elementClose('section');
  incrementalDom.elementOpenStart('footer');
      incrementalDom.attr('class', 'modal-footer');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('button');
      incrementalDom.attr('type', 'button');
      incrementalDom.attr('class', 'btn btn-primary');
      incrementalDom.attr('onClick', opt_data.close);
  incrementalDom.elementOpenEnd();
  incrementalDom.text('OK');
  incrementalDom.elementClose('button');
  incrementalDom.elementClose('footer');
  incrementalDom.elementClose('div');
  incrementalDom.elementClose('div');
  incrementalDom.elementClose('div');
};
exports.render = $render;
if (goog.DEBUG) {
  $render.soyTemplateName = 'Modal.render';
}

exports.render.params = ["body","close","header","shown"];
exports.render.types = {"body":"any","close":"any","header":"any","shown":"any"};
templates = exports;
return exports;

});

class Modal extends Component {}
Soy.register(Modal, templates);
export { Modal, templates };
export default templates;
/* jshint ignore:end */
