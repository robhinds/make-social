var userProfileApp = userProfileApp || {Collections: {}, Models: {}, Views: {}};
$(function(){
	userProfileApp.Models.NotificationModel = Backbone.Model.extend({
		defaults: function() {
			return {
				message: "",
				author: "",
				date: "",
				type: ""
			};
		}
	});
}());