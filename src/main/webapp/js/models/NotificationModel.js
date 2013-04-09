$(function(){
	window.TimelineApp = TimelineApp || {Collections: {}, Models: {}, Views: {}};
	
	TimelineApp.Models.NotificationModel = Backbone.Model.extend({
		defaults: function() {
			return {
				message: "",
				author: "",
				date: ""
			};
		}
	});
}());