	var locales = java.util.Locale.getISOCountries();
 
	for (var i=0;i<locales.length;i++) {
 
     		var countryCode = locales[i];
		var obj = new java.util.Locale("", countryCode);
 
		println("Country Code = " + obj.getCountry() 
			+ ", Country Name = " + obj.getDisplayCountry());
 
	}
 
	println("Done");
