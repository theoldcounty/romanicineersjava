var ExpandList = Backbone.View.extend({
	
	$rootElements: null,
	$currentClickedElement: null,
	$wrapperElement: null,
	
	initialize: function(){
		this.$rootElements = this.$el.find('.list-item');
		this.$prevClickedRootElement = $([]);
		this.bindEvents();
	},
	
	bindEvents: function() {
		var self = this;
		this.$rootElements.bind('click', _.bind(this.onListItemClick, this));
		this.$rootElements.find("a").each(function(){
			$(this).bind('click', _.bind(self.onLinkClick, self));
		});
	},
	
	onListItemClick: function(event) {
		event.preventDefault();
		this.$currentClickedElement = $(event.currentTarget);
		
		this.$wrapperElement = this.$currentClickedElement.find(".wrapper");
		//checks if wrapper has child elements
		if(this.$wrapperElement.children().size() > 0) {
			if(!this.$currentClickedElement.hasClass("expanded")){
				this.expandElement();
			} else {
				this.foldElement(this.$currentClickedElement);
			}
		}
	},
	
	onLinkClick: function(event) {
		var $element = $(event.currentTarget);
		
		/*
		 * if element has class "link-big" then trigger click event for parent element to expand list
		 */
		if($element.hasClass("link-big")) {
			event.preventDefault();
			$element.parent().trigger('click');
			return false;
		}
		
		var target = $element.attr("target");
		if(target != undefined) {
			window.open($element.attr("href"), $element.attr("target"));
		} else {
			window.open($element.attr("href"));
		}
	},
	
	expandElement: function() {
		var self = this;
		this.$rootElements.each(function() {
			if($(this).hasClass("expanded")) {
				self.foldElement($(this));
			}
		});
		this.$currentClickedElement.addClass("expanded");
		this.showHiddenElement(this.$wrapperElement);
	},
	
	foldElement: function($element) {
		$element.removeClass("expanded");
		$element.find(".wrapper").hide();
	},
	
	showHiddenElement: function($element) {
		$element.show();
	}
});

//EVENTS DEFINITION
ExpandList.CreateExpandListEvent = "MCD-CreateExpandListEvent";

$(function() {
	//creates ExpandList on element which is passed along in the event
	$(document).bind(ExpandList.CreateExpandListEvent, function(event) {
		event.element.find("[data-role='expand-list']").each(function() {
			new ExpandList({el: this});
		});
	});
	createExpandList();
});

createExpandList = function() {
	$("[data-role='expand-list']").each(function() {
		new ExpandList({el: this});
	});
};
