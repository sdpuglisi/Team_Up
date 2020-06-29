$(document).ready(function(){
	
	/* Reload all user skills */
	$.ajax({
	  type: "POST",
	  url: "http://localhost:8080/TeamUp/getSkills?user=" + $('#user-username').val(),
	}).done(function(responseJson) {
		if(typeof responseJson !== undefined && responseJson !== null && responseJson.length > 0) {
			$.each(responseJson, function(index, item) {
				$('<i class="fa fa-times-circle delete-circle" style="display:none"></i>').appendTo($('.profile-work'));
				var skill = $('<a class="single-skill">').text(' '+item.skillName).appendTo($('.profile-work'));
				$('<br/>').appendTo(skill);
			});
			var controlSkills = $('<div class="control-skills" style="padding-top:10px; float:left">').appendTo($('.profile-work'));
			$('<a class="add-skill" data-toggle="modal" data-target="#addSkillModal">').text('Add').appendTo(controlSkills);
			$('<a class="delete-skill" href="#" >').text('Delete').appendTo(controlSkills);
			$('<a class="cancel" style="display:none" href="#">').text('Cancel').appendTo(controlSkills);
		} else {
			$('<p style="font-style:italic; margin-top:0"></p>').text('No skills added yet').appendTo($('.profile-work'));
			var controlSkills = $('<div class="control-skills" style="padding-top:10px; float:left">').appendTo($('.profile-work'));
			$('<a class="add-skill" data-toggle="modal" data-target="#addSkillModal">').text('Add').appendTo(controlSkills);
		}
	});
	
	$('#login-username').on("focus", function() {
		if($('.ui.error.message').is(':visible')) {
			$('.ui.error.message').hide();
		}
	});
	
	$('#login-password').on("focus", function() {
		if($('.ui.error.message').is(':visible')) {
			$('.ui.error.message').hide();
		}
	});
	
	$("input[data-type='currency']").on({
	    keyup: function() {
	      formatCurrency($(this));
	    },
	    blur: function() { 
	      formatCurrency($(this), "blur");
	    }
	});
	
	$('#my-account-password').on("focus", function() {
		$(this).val('');
		$('.edit-password-container').hide();
		$('#confirm-password-container').show();
	});
	
	/*$('#my-account-password').focusout(function() {
		$(this).val('12345678');
		$('#confirm-password-container').hide();
		$('.edit-password-container').show();
	});*/
	
	$('.edit-password-container').on("click", function() {
		$('#my-account-password').focus();
	});
	
    $("#my-tab a").click(function(e){
        e.preventDefault();
        $(this).tab('show');
    });
    
    /* Handles the click on the search dropdown item */
    $('#myDropdown a').on('click', function() {
    	$('#myInput').val($(this).text());
    	$('#myDropdown').removeClass("show");
    	$('#arrow-up-search').hide();
    	$('#arrow-down-search').show();
    });
    
    $('#myInput').focusout(function() {
    	if($('#myDropdown').hasClass("show")) {
    		$('#myDropdown').removeClass("show");
    		$('.clear-search-textbox').hide();
    		$('#arrow-down-search').show();
    	}
    });
    
    $('#arrow-down-search').on("click", function() {
    	$('#arrow-down-search').hide();
    	//$('#arrow-up-search').show();
    	$('.clear-search-textbox').show();
    	$('#myDropdown').addClass("show");
    });
    
    $('#arrow-up-search').on("click", function() {
    	$('#arrow-up-search').hide();
    	$('#arrow-down-search').show();
    	$('#myDropdown').removeClass("show");
    });
    
    $('.clear-search-textbox').on("click", function() {
    	$('#myInput').val('');
    });
    
    $('.search-textbox').keypress(function(event){
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if(keycode == '13'){
            //TODO
        }
        /*else if(keycode == '27') {
        	if($('#myDropdown').hasClass("show")) {
        		$('#myDropdown').removeClass("show");
        		$('#myInput').focusout();
        	}
        }*/
    });
    
    $(document).on("click", ".delete-skill", function() {
    	$('.delete-circle').show();
    	$('.add-skill').hide();
    	$('.delete-skill').hide();
    	$('.cancel').show();
    });
    
    $(document).on("click", ".cancel", function() {
    	$('.delete-circle').hide();
    	$('.add-skill').show();
    	$('.delete-skill').show();
    	$('.cancel').hide();
    });
    
    $(document).on("click", ".delete-circle", function() {
    	$.ajax({
		  type: "POST",
		  url: "http://localhost:8080/TeamUp/deleteSkill?username=" + $('#user-username').val() + "&skillName=" + $(this).next('a').text().trim()
		}).done(function(response) {
			location.reload();
		});
    });
    
    $(document).on("click", ".ui.basic.green.button", function() {
    	$.ajax({
  		  type: "POST",
  		  url: "http://localhost:8080/TeamUp/acceptCollaboration?projectId=" + $(this).parents('.ui.cards').data("request").projectId + "&username=" + $(this).parents('.ui.cards').data("request").senderUsername
  		}).done(function(response) {
  			location.reload();
  		});
    });
    
    $(document).on("click", ".ui.basic.red.button", function() {
    	$.ajax({
  		  type: "POST",
  		  url: "http://localhost:8080/TeamUp/declineCollaboration?projectId=" + $(this).parents('.ui.cards').data("request").projectId + "&username=" + $(this).parents('.ui.cards').data("request").senderUsername
  		}).done(function(response) {
  			location.reload();
  		});
    });
    
    $('.close-modal').on("click", function() {
    	$('#my-project-detail-container').hide();
    	$('#projects').show();
    });
    
    $('#myTab li.nav-item').on("click", function() {
    	if($('#my-project-detail-container').is(':visible')) {
    		$('#my-project-detail-container').hide();
    		$('#projects').show();
    	}
    });
    
    $(document).on("click", ".project-title", function() {
    	$('#projects').hide();
    	//$('.tab-content').html('<i class="fa fa-spinner fa-spin" style="font-size:24px"></i>');
    	$.ajax({
    		  url: "http://localhost:8080/TeamUp/ProjectInfo?project_name=" + $(this).text() + "&leader=" + $('#user-username').val(),
		}).done(function(responseJson) {
			var collaborators = '';
			$('#collaborators-list').html("");
			
			if(typeof responseJson.members !== 'undefined' && responseJson.members !== null) {
				collaborators = responseJson.members.split(",");
			}
			$('#my-project-detail-container').show();
			$('#my-project-title').text(responseJson.title);
			$('#my-project-category').text(responseJson.category);
			$('#my-project-description').text(responseJson.description);
			if(responseJson.status === 'created') {
				$('#my-project-status').html('<p>This project has been <span style="font-weight:500">created</span> but not already started.</p>');
			} else if(responseJson.status === 'in progress') {
				$('#my-project-status').html('<p>This project is currently <span style="font-weight:500">in progress</span>.</p>');
			} else if (responseJson.status === 'ended') {
				$('#my-project-status').html('<p>This project <span style="font-weight:500">ended</span>.</p>');
			} else if (responseJson.status === 'stopped') {
				$('#my-project-status').html('<p>This has been <span style font-weight="500">stopped</span>.</p>');
			}
			
			var divRootList = $('<div class="ui list">').appendTo($('#collaborators-list'));
			var divItem = $('<div class="item">').appendTo(divRootList);
			$('<img class="ui avatar image" src="images/profile_logo.png">').appendTo(divItem);
			var divContent = $('<div class="content">').appendTo(divItem);
			$('<a class="header">').text($('#user-username').val().trim()).appendTo(divContent);
			$('<div class="description">').text('Leader').appendTo(divContent);
			
			if(typeof collaborators !== undefined && collaborators !== null && collaborators !== '') {
				$.each(collaborators, function(index, item) {
					var divRootList = $('<div class="ui list">').appendTo($('#collaborators-list'));
					var divItem = $('<div class="item">').appendTo(divRootList);
					$('<img class="ui avatar image" src="images/profile_logo.png">').appendTo(divItem);
					var divContent = $('<div class="content">').appendTo(divItem);
					$('<a class="header">').text(item).appendTo(divContent);
					$('<div class="description">').text('Teammate').appendTo(divContent);
					
					//$('<li class="list-group-item"></li>').html('<a href="mailto:' + item + '">' + item + '</a>').appendTo($('#collaborators-list'));
				});
			} else {
				$('<p style="font-style:italic"></p>').text('No teammates found for this project').appendTo($('#collaborators-list'));
			}
			$('#my-project-budget').html('For the <b>' + responseJson.title + '</b> project, you requested ' + '<span style="font-size:16px">' + responseJson.budgetRequested + '</span>');
		});
    });
    
    $('.search-project-card .header a').on("click", function() {
    	$.ajax({
    		type: "POST",
  		  	url: "http://localhost:8080/TeamUp/ProjectInfo?project_name=" + $(this).text() + "&leader=" + $('#user-username').val(),
		}).done(function(responseJson) {
			//$('#search-project-details-container').show();
			$('#search-project-title').text(responseJson.title);
			$('#search-project-category').text(responseJson.category);
			$('#search-project-description').text(responseJson.description);
			$('#search-project-budget').html('For the <b>' + responseJson.title + '</b> project, you requested ' + '<span style="font-size:16px">\$' + responseJson.budgetRequested + '</span>');
		});
    });
    
    $('#edit-project-button').on('click', function() {
    	$.ajax({
    		type: "POST",
  		  	url: "http://localhost:8080/TeamUp/editProject?leader=" + $('#user-username').val().trim() + "&oldProjectTitle=" + $('#my-project-title').text().trim() + "&editProjectName=" + $('#edit-project-name').val().trim() + "&editCategory=" + $('#edit-category').val() + "&editDescription=" + $('#edit-description').val().trim() + "&editStatus=" + $('#edit-status').val(),
		}).done(function(responseJson) {
			//TODO
		});
    })
    
    $(document).on("click", "#delete-project-button", function() {
    	$.ajax({
    		type: "POST",
    		url: "http://localhost:8080/TeamUp/deleteProject?projectName=" + $('#my-project-title').text() + "&leader=" + $('#user-username').val(),
  		}).done(function(response) {
  			$('.modal-footer .btn.btn-secondary').click();
  			location.reload();
  		});
    });
    
    /* Handles the rating */
    $('.rating span').on("click", function() {
    	var rating = $(this).attr('id');
    	var divRating = $(this).parents('.search-project-card').find('div.rating');
    	$.ajax({
    		type: "POST",
  		  	url: "http://localhost:8080/TeamUp/insertRating?project_name=" + $(this).parents('.search-project-card').find('div.header a').text().trim() + "&leader=" + $(this).parents('div.extra.content').find('.right.author').text().trim() + "&username=" + $('#userAccount').val() + "&rating=" + rating,
		}).done(function(responseJson) {
			if(typeof responseJson !== 'undefined' && responseJson.response !== null && responseJson.includes('OK')) {
				$(divRating).html('<i style="color:green" class="fa fa-check" aria-hidden="true"></i> Your request has been sent with success!');
			} else {
				$(divRating).html('<i style="color:orangered" class="fa fa-times" aria-hidden="true"></i> Your request sending failed!');
			}
		});
    });
    
});

/**
 * This function handles the clear of all form fields 
 * @param formId the form the clear 
 * @returns
 */
function resetForm(formId) {
	$(':text, :password, :file', formId).val('');
    $(':input,select option, checkbox', formId).removeAttr('checked').removeAttr('selected');
    $('select option:first', formId).attr('selected',true);
    
    if($('textarea').length) {
    	$('textarea').val('');
    }
}

/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction() {
  $('#myDropdown').addClass("show");
  $('#arrow-down-search').hide();
  //$('#arrow-up-search').show();
  $('.clear-search-textbox').show();
}

function filterFunction() {
	$('#arrow-up-search').hide();
	$('.clear-search-textbox').show();
  var input, filter, ul, li, a, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  div = document.getElementById("myDropdown");
  a = div.getElementsByTagName("a");
  for (i = 0; i < a.length; i++) {
    txtValue = a[i].textContent || a[i].innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      a[i].style.display = "";
    } else {
      a[i].style.display = "none";
    }
  }
}

/* Displays all projects created by the current user */
function findMyProjects(baseURL) {
	
	$.ajax({
		type: "POST",
		url: baseURL,
	}).done(function(responseJson) {
		if(typeof responseJson !== undefined && responseJson !== null && responseJson.length > 0) {
			const cardRootNode = document.getElementById("projects");
			var removedChild = false;
			while (cardRootNode.firstChild) {
				cardRootNode.removeChild(cardRootNode.lastChild);
				removedChild = true;
			}
			$.each(responseJson, function(index, item) {
				if(removedChild) {
					//$('<h4 class="mt-2">').html('<strong>My Projects</strong>').appendTo($('#projects'));
					removedChild = false;
				}
				var date = item.creationDate.substring(0,16);
				
				var divCard = $('<div class="ui card fluid">').appendTo($('#projects'));
				var divContent = $('<div class="content">').appendTo(divCard);
				var divHeader = $('<div class="header">').appendTo(divContent);
				$('<a class="project-title" href="#">').text(item.title).appendTo(divHeader);
				var divMeta = $('<div class="meta">').appendTo(divContent);
				$('<span class="right floated time">').text("Posted on " + date).appendTo(divMeta);
				$('<span>').text(item.category).appendTo(divMeta);
				var divDescription = $('<div class="description">').appendTo(divContent);
				$('<p>').text(item.description).appendTo(divDescription);
				var divExtraContent = $('<div class="extra content">').appendTo(divCard);
				var divAuthor = $('<div class="right floated author">').appendTo(divExtraContent);
				$('<p>').text('This is a your project').appendTo(divAuthor);
			});
		} else {
			const cardRootNode = document.getElementById("projects");
			while (cardRootNode.firstChild) {
				cardRootNode.removeChild(cardRootNode.lastChild);
			}
			$('<h4 style="font-style:italic"></h4>').text('You have not created projects yet.').appendTo($('#projects'));
			$('<p></p>').text('Create one by the appropriate section.').appendTo($('#projects'));
		}
	});
}

/* Displays all projects the user collaborates on */
function viewCollaborations(url) {
	$.ajax({
		type: "POST",
		url: url,
	}).done(function(responseJson) {
		if(typeof responseJson !== undefined && responseJson !== null && responseJson.length > 0) {
			const cardRootNode = document.getElementById("collaborations");
			var removedChild = false;
			while (cardRootNode.firstChild) {
				cardRootNode.removeChild(cardRootNode.lastChild);
				removedChild = true;
			}
			$.each(responseJson, function(index, item) {
				if(removedChild) {
					//$('<h4 class="mt-2">').html('<strong>Collaborations</strong>').appendTo($('#collaborations'));
					removedChild = false;
				}
				var date = item.creationDate.substring(0,16);
				
				var divCard = $('<div class="ui card fluid">').appendTo($('#collaborations'));
				var divContent = $('<div class="content">').appendTo(divCard);
				var divHeader = $('<div class="header">').appendTo(divContent);
				$('<a href="http://localhost:8080/TeamUp/projectView?projectName=' + item.title.trim() + '&leader=' + item.leader.trim() +'" class="collaboration-project-title">').text(item.title).appendTo(divHeader);
				var divMeta = $('<div class="meta">').appendTo(divContent);
				$('<span class="right floated time">').text("Posted on " + date).appendTo(divMeta);
				$('<span>').text(item.category).appendTo(divMeta);
				var divDescription = $('<div class="description">').appendTo(divContent);
				$('<p>').text(item.description).appendTo(divDescription);
				var divExtraContent = $('<div class="extra content">').appendTo(divCard);
				var divAuthor = $('<div class="right floated author">').appendTo(divExtraContent);
				$('<p>').text(item.leader).appendTo(divAuthor);
			});
		} else {
			const cardRootNode = document.getElementById("collaborations");
			while (cardRootNode.firstChild) {
				cardRootNode.removeChild(cardRootNode.lastChild);
			}
			$('<h4 style="font-style:italic"></h4>').text('You have not started any collaborations yet.').appendTo($('#collaborations'));
		}
	});
}

/* Displays all collaboration requests */
function findCollaborationRequests(baseURL) {
	$.ajax({
		  url: baseURL
		}).done(function(responseJson) {
			if(typeof responseJson !== undefined && responseJson !== null && responseJson.length > 0) {
				const cardRootNode = document.getElementById("teams");
				var removedChild = false;
				while (cardRootNode.firstChild) {
					cardRootNode.removeChild(cardRootNode.lastChild);
					removedChild = true;
				}
				$.each(responseJson, function(index, item) {
					var divUICards = $('<div class="ui cards">').data( "request", {projectId: item.projectId, senderUsername: item.senderUsername}).appendTo($('#teams'));
					var divCard = $('<div class="card">').appendTo(divUICards);
					var divContent = $('<div class="content">').appendTo(divCard);
					//var divHeader = $('<div class="header">').html('<a href="http://localhost:8080/TeamUp/userInformation?username=' + $('.ui.cards').data("request").senderUsername + '">' + item.senderFirstname + ' ' + item.senderLastname + '</a>').appendTo(divContent);
					var divHeader = $('<div class="header">').text(item.senderFirstname + ' ' + item.senderLastname).appendTo(divContent);
					//$('.ui.cards .header').text(item.senderFirstname + ' ' + item.senderLastname);
					var divMeta = $('<div class="meta">').text(item.senderProfession).appendTo(divContent);
					//$('.ui.cards .meta').text("Computer Scientist");
					var divDescription = $('<div class="description">').html(item.senderFirstname + " would like to be part of your team for the project <strong>" + item.projectTitle + "</strong>").appendTo(divContent);
					//$('.ui.cards .description').html(item.senderFirstname + " would like to be part of your team for the project <strong>" + item.projectTitle + "</strong>");
					var divExtraContent = $('<div class="extra content">').appendTo(divCard);
					var divTwoButtons = $('<div class="ui two buttons">').appendTo(divExtraContent);
					var divAcceptButton = $('<div class="ui basic green button">').appendTo(divTwoButtons);
					$('.ui.basic.green.button').text('Accept');
					var divDeclineButton = $('<div class="ui basic red button">').appendTo(divTwoButtons);
					$('.ui.basic.red.button').text('Decline');
				});
		} else {
			const cardRootNode = document.getElementById("teams");
			while (cardRootNode.firstChild) {
				cardRootNode.removeChild(cardRootNode.lastChild);
			}
			$('<h4 style="font-style:italic"></h4>').text('You have not received any collaboration requests yet').appendTo($('#teams'));
		}
	});
}

function formatNumber(n) {
  // format number 1000000 to 1,234,567
  return n.replace(/\D/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",")
}

function formatCurrency(input, blur) {
  // appends $ to value, validates decimal side
  // and puts cursor back in right position.
  
  // get input value
  var input_val = input.val();
  
  // don't validate empty input
  if (input_val === "") { return; }
  
  // original length
  var original_len = input_val.length;

  // initial caret position 
  var caret_pos = input.prop("selectionStart");
    
  // check for decimal
  if (input_val.indexOf(".") >= 0) {

    // get position of first decimal
    // this prevents multiple decimals from
    // being entered
    var decimal_pos = input_val.indexOf(".");

    // split number by decimal point
    var left_side = input_val.substring(0, decimal_pos);
    var right_side = input_val.substring(decimal_pos);

    // add commas to left side of number
    left_side = formatNumber(left_side);

    // validate right side
    right_side = formatNumber(right_side);
    
    // On blur make sure 2 numbers after decimal
    if (blur === "blur") {
      right_side += "00";
    }
    
    // Limit decimal to only 2 digits
    right_side = right_side.substring(0, 2);

    // join number by .
    input_val = "$" + left_side + "." + right_side;

  } else {
    // no decimal entered
    // add commas to number
    // remove all non-digits
    input_val = formatNumber(input_val);
    input_val = "$" + input_val;
    
    // final formatting
    if (blur === "blur") {
      input_val += ".00";
    }
  }
  
  // send updated string to input
  input.val(input_val);

  // put caret back in the right position
  var updated_len = input_val.length;
  caret_pos = updated_len - original_len + caret_pos;
  input[0].setSelectionRange(caret_pos, caret_pos);
}

function deleteAllCookies() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
}

function logoutFunction() {
	deleteAllCookies();
	window.location.replace("http://localhost:8080/TeamUp/");
}

function editProject() {
	$('#edit-project-name').val($('#my-project-title').text().trim());
	$('#edit-category').val($('#my-project-category').text().trim()).change();
	$('#edit-description').val($('#my-project-description').text());
	$('#edit-budget').val($('#my-project-budget').text().trim().substring($('#my-project-budget').text().trim().indexOf('$'),$('#my-project-budget').text().trim().length));
	$('#edit-status').val($('#my-project-status span').text().trim()).change();
	
}