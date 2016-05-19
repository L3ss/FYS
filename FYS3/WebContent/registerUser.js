/**
 * 
 */

function registerClick(e) {
	require(["dojo/dom","dijit/registry","dojo/dom-style"], function(dom, registry, domStyle){
		var regFirstName = registry.byId("registerFirstName").get("value");
		var regLastName = registry.byId("registerLastName").get("value");
		var regEmail = registry.byId("registerEmail").get("value");
		var regPassword = registry.byId("registerPassword").get("value");
		var regDOB = registry.byId("registerDOB").get("value");
		var regIdNumber = registry.byId("registerIdNumber").get("value");
		var regIdExpiry = registry.byId("registerIdExpiryDate").get("value");
		
		
		
		if (regFirstName == "") {
			domStyle.set("registerFirstName", {
				"border-color" : "red",
				"border-width" : "2px"
			});
		} else if (regLastName == "") {
			alert("Last Name has to be a valid name");
		} else if (!validEmail(regEmail)) {
			alert("Email address is not valid!")
		} else if (regPassword.length < 8) {
			alert("Your password isn't long enough")
		} else {
			var xhrArgs = {
	    		url: "http://fys.rgbaz.com/",
	    		postData: dojo.toJson({function:"register", email:regEmail, firstname:regFirstName, lastname:regLastName, dob:regDOB, personcode:regIdNumber, ID_exp_date:regIdExpiry, password:regPassword}),
	    		handleAs: "text",
	    		load: function(data){
	    			dom.byId("regResponse").innerHTML = data;
	    		},
	    		error: function(error){
	    			dom.byId("regResponse").innerHTML = "Er is iets fout gegaan :(</br>" + error;
	    		}
		    }
		    dom.byId("regResponse").innerHTML = "Verzoek aan het versturen...";
			var deferred = dojo.xhrPost(xhrArgs);
			
		}
	    
	    
	    
	});
}

function validEmail(email) {
	var validEmailRegEx = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/i;
	
	if (validEmailRegEx.test(email)) {
		return true;
	} else {
		return false;
	}
}