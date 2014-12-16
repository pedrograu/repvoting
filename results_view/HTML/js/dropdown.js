// dropdown menu

function set_hide_timeout(menu_row_li) {
	unset_hide_timeout(menu_row_li);
	menu_row_li.get()[0].hide_timeout = window.setTimeout(function() { hide_submenu(menu_row_li) }, 400);
}

function unset_hide_timeout(menu_row_li) {
	window.clearTimeout(menu_row_li.get()[0].hide_timeout);
	menu_row_li.get()[0].hide_timeout = null;
}

function create_submenu(menu_row_li) {
	ul = $("<ul class='submenu_list' style='visibility: hidden;'></ul>");
	menu_row_li.append(ul);
	return ul;
}

function append_element_to_submenu(submenu, text, href) {
	li = $("<li></li>");
	a = $("<a></a>");
	a.attr("href", href);
	a.text(text);

	li.mouseover(function() {
		unset_hide_timeout(submenu.parent());
	});
	
	li.mouseout(function() {
		set_hide_timeout(submenu.parent());
	});
	
	a.mouseover(function() {
		unset_hide_timeout(submenu.parent());
	});
	
	a.mouseout(function() {
		set_hide_timeout(submenu.parent());
	});
	
	submenu.append(li);
	li.append(a);
}

function hide_submenu(menu_row_li) {
	menu_row_li.children(".submenu_list").css("visibility", "hidden");
}

function show_submenu(menu_row_li) {
	menu_row_li.children(".submenu_list").css("visibility", "visible");
}

function hide_all_submenus() {
	$(".submenu_list").css("visibility", "hidden");
}

