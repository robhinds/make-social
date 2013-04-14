var userProfileApp = userProfileApp || {Collections: {}, Models: {}, Views: {}};
(function () {
	userProfileApp.Views.UserProfileView = Backbone.View.extend({
		
		el: '#user-profile-page',
		
		events: {
			'click #post-status ': 'postStatus'
		},
		
		initialize: function () {
			this.status = this.$( '#new-status' );
			
			//setup listeners for notification timeline
			window.userProfileApp.Collections.Notifications.on('add', this.addOne, this);
			window.userProfileApp.Collections.Notifications.on('reset', this.addAll, this);
			window.userProfileApp.Collections.Notifications.on('add', this.render, this);
			
			//initial load of timeline on first page load
			window.userProfileApp.Collections.Notifications.fetch();
		},

		// This is the overall userprofile page view - rendering will be handled by other views
		render: function () {return this;},
		
		addOne: function( notification ) {
			var view = new userProfileApp.Views.NotificationView({ model: notification });
			$('#notification-timeline').prepend( view.render().el );
		},
		
		//smash the current timeline and reload from the loaded collection
		addAll: function() {
			this.$('#notification-timeline').html('');
			userProfileApp.Collections.Notifications.each(this.addOne, this);
		},
		
		postStatus: function( e ) {
			userProfileApp.Collections.Notifications.create( {message:this.status.val(), type:'STATUS', author:'you', date:'just now'} );
			this.status.val('');
		}
	});
}());