package tests;

import java.io.File;
import java.net.URL;

public class niktamer {

	public static void main(String[] args) {
		final String repoBase = new File(".").getAbsolutePath();
		final String repoCartes = (new File("./cartes").getAbsolutePath());
		File f = new File(repoCartes);
		System.out.println(repoCartes);
	}
	

}
