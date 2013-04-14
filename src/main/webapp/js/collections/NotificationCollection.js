var userProfileApp = userProfileApp || {Collections: {}, Models: {}, Views: {}};
$(function(){	
	var NotificationList = Backbone.Collection.extend({
		model: userProfileApp.Models.NotificationModel,
		url: window.core.page.appRoot + '/user/timeline',
		
		projects: function() {
			return this.filter(function( notification ) {
				return notification.get( 'type' ) == 'PROJECT';
			});
		}
	});
	
	//intialise empty list in namespace
	userProfileApp.Collections.Notifications = new NotificationList(); 
}());