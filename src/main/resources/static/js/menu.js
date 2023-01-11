$('.navbar-nav a').on('click', function() {
	$('.navbar-nav').find('li.nav-item.active').each(function() {
		$(this).parent('li').removeClass('nav-item.active');
	});
	$(this).parent('li').addClass('nav-item.active');
});

$('.dropdown-menu a.dropdown-toggle').on(
		'click',
		function(e) {
			if (!$(this).next().hasClass('show')) {
				$(this).parents('.dropdown-menu').first().find('.show')
						.removeClass("show");
			}
			var $subMenu = $(this).next(".dropdown-menu");
			$subMenu.toggleClass('show');

			$(this).parents('li.nav-item.dropdown.show').on(
					'hidden.bs.dropdown', function(e) {
						$('.dropdown-submenu .show').removeClass("show");
					});

			return false;
		});