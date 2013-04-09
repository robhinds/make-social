$(function(){
	window.TimelineApp = TimelineApp || {Collections: {}, Models: {}, Views: {}};
	
	TimelineApp.Collections.NotificationCollection = Backbone.Collection.extend({
		model: TimelineApp.Models.NotificationModel
	});
}());