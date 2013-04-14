var userProfileApp = userProfileApp || {Collections: {}, Models: {}, Views: {}};
(function () {
	userProfileApp.Views.NotificationView = Backbone.View.extend({
		tagName: 'div',
		template: _.template( $('#notification-template').html() ),
		
		initialize: function() {
			this.model.on( 'change', this.render, this );
		},

		// populate the html to the dom
		render: function() {
			this.$el.html( this.template( this.model.toJSON() ) );
			return this;
		}
	});
}());