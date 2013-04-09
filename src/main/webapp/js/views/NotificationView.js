(function () {
	window.TimelineApp = TimelineApp || {Collections: {}, Models: {}, Views: {}};
	
	TimelineApp.Views.NotificationView = Backbone.View.extend({
		
		initialize: function (options) {
			this.notification = options.notification;
		},

		// populate the html to the dom
		render: function () {
			this.$el.html(_.template($('#notification-template').html(), this.note.toJSON()));
			return this;
		}
	});
}());