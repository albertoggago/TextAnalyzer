package com.alberto.analyzer.config;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigLiferay extends Config {

	public ConfigLiferay (){
	
		super();
		
		paramBasicTaxRate = 10;

		paramTokensZeroBasicTaxRate = createListTokensTaxes(listTokensZeroBasicTaxRate);

		paramAdditionalTaxRate = 5;

		String[] listTokensAdditionalTaxRate = {"imported"};
		paramTokensAdditionalTaxRate = createListTokensTaxes(listTokensAdditionalTaxRate);
		
		String[] listIgnoreChar = {"\n",","};
		paramIgnoreChar = getListCharsfromString(listIgnoreChar);

		String[] listIgnoreTokens = {"at","•"};
		paramIgnoreTokens = getListStringfromString(listIgnoreTokens);

		paramInitPrintText = "• ";
		paramEndPrintText = ": ";
		
		paramMainBodySalesTaxes = "Sales Taxes";
		paramMainBodyTotal = "Total";
	
	}

	private static List<String> createListTokensTaxes(String[] arrayTokens) {
		return (List<String>) Stream.of(arrayTokens)
				.collect(Collectors.toList());
	}

	private static List<Character> getListCharsfromString(String[] stringsToChar) {
		String charToIgnore = Stream.of(stringsToChar)
		           .collect(Collectors.joining());
		return charToIgnore.chars()
				           .mapToObj(c -> (char) c)
				           .collect(Collectors.toList());
	}

	private static List<String> getListStringfromString(String[] stringIgnoreWords) {
		return (List<String>) Stream.of(stringIgnoreWords)
						.map(Object::toString)
						.collect(Collectors.toList());
	}
	
	String[] listTokensZeroBasicTaxRate = {"book","chocolates","chocolate","headache","pills",
			"taxRate","medication","medicament","medicate",
			"medications","drugs","medicaments",
			"pharmaceuticals","therapies","treatments","meds","pills",
			"Pharmaceutical","medicinal","prescriptions","drug","pharmacy",
			"medical","remedies","cures","pharma","substances","products",
			"substance","Arzneimittel","Hms","vaccines","analgesics",
			"paracetamol","antibiotic","Actifed","Lomotil","antipyretics",
			"mebendazole","Viracept",
			"prescription","Proventil","Rocephin","Retrovir","Relafen","plaquenil",
			"contraceptives","Homoeopathic","ranitidine","painkillers","Dimetapp",
			"decongestants","chloroquine","dosages","maleate",
			"formulations","foodstuffs","Lanoxin","ointments","Lopid","Procardia",
			"penicillin","expectorants","Atarax","Restoril","Indocin","Hytrin",
			"acetaminophen","homeopathic","Sporanox","antiseptics","pharmacies",
			"streptokinase","anticonvulsants","cycloserine","phenylpropanolamine",
			"panadol","bufferin","gelcap","Norvir","meclizine","therapeutics",
			"dextroamphetamine","cimetidine","cordarone","Tagamet","ibuprofen",
			"pyrimethamine","Vasotec","kaopectate","antibacterials","saquinavir",
			"Phenergan","lovastatin","cephalosporin","sulfamethoxazole","Inderal",
			"Lescol","erythromycin","marrow","pith","substance","nub","core","kernel",
			"heart","essence","gist","sum","center","inwardness","nitty-gritty","beef",
			"pork","chicken","veal","venison","steaks",
			"poultry","steak","hamburger","food","brisket","bacon","pig","slaughter",
			"livestock","roast","broiler","meatloaf","sandwich","flesh","bushmeat",
			"roasting","fowl","animal","carne","edible","goose","bone","skin","bull",
			"incineration","hunt","Siu","foam","prey","mass","ground","hectare","table",
			"burning","destruction","mitt","pitch","crux","corral","mesa","estate",
			"cannon","bureau","carnes","Bethlehem","meso","lehm","chair","lamb","mutton",
			"boneless","seafood","offal","horsemeat","forequarter",
			"turkey","sausages","cervelat","chevon","potatoes","omophagic",
			"griskin","loin","dairy","fillets","headcheese",
			"rullichies","foods","paupiette","smokies","cheese","tuna",
			"tenderloin","gull","sucker",
			"fool","chump","schlemiel","mug","Patsy","mark","angle","shlemiel","trout","catfish",
			"salmon","sturgeon","halibut","swordfish","cod","carp","fishery","mackerel","redfish",
			"seafood","marlin","paddlefish","finfish","sockeye","whitefish","dogfish","eel","fishermen",
			"fisheries","fisherman","hatchery","fishers","fishies","anglers","sprats","whiting","codfish",
			"birds","groundfish","aquaculture","goldfish","shark","molluscs","species","dace","fowl","mullet",
			"aquatic","mammals","angling","piscine","water","spawning","marine","river","snapper","amphibians"
			,"tributaries","habitat","Poisson","mercury","harvesters","fishy","troll","sea","harvest","peaches",
			"mosses","char","catch","harvesting","peach","sludge","catches","quarry","culturist","fang",
			"slurry","stocks","rib","bleu","torpedo","yue","vascular","Pez","live","stock","thickness","hole",
			"sin","pisces","capture","Hal","blue","sole","Milky","opaque","whitish","milklike","dairy","egg",
			"leche","soup","suckler","lactation","lactate","breastfeeding","cream","pudding","formula",
			"milkman","latte","infant","lait","breast","disinfection","bin","slag","diary","dross","Albanian",
			"midst","albanians","full","exploit","complete","breast-feeding","breastmilk","lechero",
			"butterfat","yogurt","soymilk","yoghurt","buttermilk","sugar","juice","colostrum","syrup",
			"lactobutyrometer","agalactia","milkful","emulge","whey","butyrometer","lactage","lacteally",
			"cereal","lactogen","dairywoman","lactodensimeter","honey","meat","mislactation","rambooze",
			"panshon","lactoprotein","lactarene","lactific","flour","lactometer","cows","cheese","spinach",
			"chocolate","ziega","renneting","agalactous","lactose","cola","galactophorous","curd",
			"lactescence","creameries","yield","berries","berry","apple","pear","apples","vegetables",
			"vegetable","grapefruit","pineapple","orchard","prunes","succulent","harvest","lime","seafood",
			"shellfish","nuts","blossom","pudding","produce","fresh","product","rewards","fruitful","Pago",
			"result","stuff","consequence","benefits","growing","outcome","results","payoff","fruition",
			"output","fag","crystallization","gains","dividends","dividend","achievement","outgrowth",
			"benefit","difference","effect","culmination","avail","brainchild","success","successful","Kwo",
			"relevancy","clown","figment","blackspot","Frutos","Guo","Oaa","Obst","ffv","mango","peach",
			"cherries","pears","grapes","peaches","apricots","strawberries","avocados","plums","melon",
			"raspberries","oranges","pineapples","blueberries","eggfruit","citrus","tomato","figs","guava",
			"fructescence","mombin","tangerines","nectarines","soursop","persimmons","pseudocarp","macadamias",
			"loquat","pomiculture","clementines","lanseh","dough","cabbage","kale","gelt","shekels","loot",
			"pelf","dinero","moolah","lucre","breadstuff","butter","loaf","cake","soup","bakery","baking",
			"boulangerie","food","bacon","toast","bun","fruitcake","baked","crust","chicken","wheat","rye",
			"maize","pan","fare","livelihood","hen","money","living","circle","luncheon","naps","roll","pulp",
			"bam","nap","butt","lot","work","match","brood","homework","limb","bar","bang","pain","Wai",
			"Npa","flour","pastry","sourdough","focaccia","flatbread","challah","cornbread","ciabatta",
			"brioche","pumpernickel","croissant","scone","azyme","pasta","wholewheat","panivorous",
			"breadcorn","clapbread","baguettes","barmbrack","cornmeal","porridge","manchet","cheese","wastel",
			"macaroni","naan","matzo","veggie","vegetal","vegetative","vegetational","veggies","veg",
			"potatoes","salads","fruit","fruits","pickles","peas","legumes","crops","dishes","groceries",
			"growers","greens","plants","pulses","steamed","produce","tomatoes","broccoli","cauliflower",
			"onions","melons","radishes","kale","cabbage","cucumbers","strawberries","lettuce","pears",
			"asparagus","berries","spinach","avocados","figs","herbs","apricots","peaches","blueberries",
			"mangoes","parsley","aubergines","chard","celery","watermelons","basil","lentils","leeks",
			"garlic","peppers","turnips","okra","beets","radicchio","cukes","artichokes","soups","meats",
			"bananas","persimmons","courgettes","plantains","foods","clementines","collards","carrots",
			"olives","parsnips","nectarines","jicama","favas","pumpkins","cherries","papayas","grapes",
			"cilantro","jujubes","zucchini","guavas","chilies","chickpeas","apples","yams","pineapples",
			"grain","foods","wheat","grains","cocoa","oats","corn","crops","crackers","cornflakes","cheerios",
			"grasses","flakes","pills","orangutans","wheaties","flours","sugar","foodstuffs","breads","milk",
			"confectionery","oat","soymilk","nutritional","maize","confectionary","bananas","margarines",
			"linseed","starches","soups","fats","dairy","inulin","micronutrient","erythritol","crisps",
			"macadamias","biscuits","sorghum","bran","pectin","vegetables","Splenda","wholewheat","rice",
			"oatmeal","carbohydrate","diets","lecithin","linoleic","soya","cashews","crispbread","groats",
			"potato","muesli","millets","lentils","barley","legumes","monounsaturated","snacks","groundnut",
			"turbinado","sorbitol","additives","vanaspati","yogurt","teff","lactogen","Maida","cassava",
			"script","daybook","ledger","record","volume","hold","reserve","Leger","playscript","memoir",
			"cookbook","novel","anthology","manuscript","guidebook","author","fiction","bookshop","bookstore",
			"textbook","monograph","booklet","pamphlet","literature","literary","Bible","workbook",
			"handbook","journal","chapter","diary","textbooks","casebook","read","compendium","reading",
			"publication","scrapbook","brochure","sketchbook","paper","writing","bibliographic","dictionary",
			"magazine","publishing","album","story","catalog","scripture","document","manuals","library",
			"yearbook","guide","almanac","notebook","genealogy","synopsis","compilation","libretto",
			"edition","binder","writer","papers","text","herbarium","parchment","manual","cahier","logbook",
			"Libro","calligraphy","carnet","letter","writers","directory","passbook","volumes","dossier",
			"collection","card","letters","worksheet","stuff","log","map","series","account","blueprint",
			"livre","tablecloth","wallet","inventory","list","reservation","works","catalogue","script",
			"daybook","ledger","record","volume","hold","reserve","Leger","playscript","novels","textbooks",
			"cookbooks","bestsellers","bookstores","literature","periodicals","textbook","guidebooks","copies",
			"writings","texts","compendiums","libraries","library","booksellers","schoolbooks","manuals",
			"articles","booklets","bibliography","yearbooks","papers","reading","publications","titles",
			"scriptures","publishing","authors","ledgers","publishers","workbook","notebooks","newspapers",
			"albums","stories","guides","paper","compilations","tales","collections","materials","directories",
			"cards","editions","letters","material","records","calligraphy","references","writers","alphabet",
			"readings","volumes","Libro","logs","bookkeeping","accounts","quid","kits","works","instruments",
			"wrote","wallets","digests","free","accountants","auditors","codes","pounds","registers",
			"portfolios","liras","sterling","carrying","livre","structures","registries","lira",
			"constructions","instrument","registry","contes","lbs","mouthpieces","pound","brochures",
			"cahiers","carnets"};
}
