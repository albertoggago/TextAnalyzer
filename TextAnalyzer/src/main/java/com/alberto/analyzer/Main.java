package com.alberto.analyzer;

class Main {

	public static void main(final String[] args) throws Exception {
		// Params 1 - Config File
		// 2 - Entry File
		// 3 - Output File
		if (args.length < 3) {
			System.out.println("No enought parameters");
		} else {
			System.out.println("Start process");
			ProcessCalculateTaxes processCalculateTaxes 
			      = new ProcessCalculateTaxes(args[0]);
			String[] result 
			      = processCalculateTaxes.processTaxesBasketFileList(args[1]);
			processCalculateTaxes.print(result, args[2]);
			System.out.println("End process");
		}
	}

}