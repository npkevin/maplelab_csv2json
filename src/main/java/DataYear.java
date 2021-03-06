import com.google.gson.*;
import java.io.*;

public class DataYear {

	public static JournalSet jSet = new JournalSet();

	public static int[] getYearRange(String fileName, String filePath) {
		int start = 999999;
		int end = -999999;
		int[] ans = new int[2];
		String line = null;

		try {

			FileReader fileReader = new FileReader(filePath + fileName + ".csv");
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {

				String[] lineData = line.split(",");
				int currentYear = Integer.parseInt(lineData[1]);

				// System.out.println(start + "/" + end + " <= " + currentYear);

				if (currentYear > end) {
					end = currentYear;
				}
				if (currentYear < start) {
					start = currentYear;
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file " + "'" + fileName + "'");
		}
		ans[0] = start;
		ans[1] = end;
		System.out.println(start + " - " + end);
		return ans;
	}
	

	public static void insertDataIntoJSET(String journal, String type, String data) {

		if (journal.equals("jep")) {

			if (type.equals("flat")) {
				jSet.JEP.flat = Double.parseDouble(data);
			} else if (type.equals("percussive")) {
				jSet.JEP.percussive = Double.parseDouble(data);
			} else if (type.equals("undefined")) {
				jSet.JEP.undefined = Double.parseDouble(data);
			} else if (type.equals("click/click train")) {
				jSet.JEP.click = Double.parseDouble(data);
			} else if (type.equals("non-referential")) {
				jSet.JEP.nonref = Double.parseDouble(data);
			} else if (type.equals("soundscapes")) {
				jSet.JEP.soundscapes = Double.parseDouble(data);
			} else if (type.equals("musical instrument")) {
				jSet.JEP.musical = Double.parseDouble(data);
			}

		} else if (journal.equals("jasa")) {
			if (type.equals("flat")) {
				jSet.JASA.flat = Double.parseDouble(data);
			} else if (type.equals("percussive")) {
				jSet.JASA.percussive = Double.parseDouble(data);
			} else if (type.equals("undefined")) {
				jSet.JASA.undefined = Double.parseDouble(data);
			} else if (type.equals("click/click train")) {
				jSet.JASA.click = Double.parseDouble(data);
			} else if (type.equals("non-referential")) {
				jSet.JASA.nonref = Double.parseDouble(data);
			} else if (type.equals("soundscapes")) {
				jSet.JASA.soundscapes = Double.parseDouble(data);
			} else if (type.equals("musical instrument")) {
				jSet.JASA.musical = Double.parseDouble(data);
			}

		} else if (journal.equals("app")) {
			if (type.equals("flat")) {
				jSet.APP.flat = Double.parseDouble(data);
			} else if (type.equals("percussive")) {
				jSet.APP.percussive = Double.parseDouble(data);
			} else if (type.equals("undefined")) {
				jSet.APP.undefined = Double.parseDouble(data);
			} else if (type.equals("click/click train")) {
				jSet.APP.click = Double.parseDouble(data);
			} else if (type.equals("non-referential")) {
				jSet.APP.nonref = Double.parseDouble(data);
			} else if (type.equals("soundscapes")) {
				jSet.APP.soundscapes = Double.parseDouble(data);
			} else if (type.equals("musical instrument")) {
				jSet.APP.musical = Double.parseDouble(data);
			}

		} else if (journal.equals("mp")) {
			if (type.equals("flat")) {
				jSet.MP.flat = Double.parseDouble(data);
			} else if (type.equals("percussive")) {
				jSet.MP.percussive = Double.parseDouble(data);
			} else if (type.equals("undefined")) {
				jSet.MP.undefined = Double.parseDouble(data);
			} else if (type.equals("click/click train")) {
				jSet.MP.click = Double.parseDouble(data);
			} else if (type.equals("non-referential")) {
				jSet.MP.nonref = Double.parseDouble(data);
			} else if (type.equals("soundscapes")) {
				jSet.MP.soundscapes = Double.parseDouble(data);
			} else if (type.equals("musical instrument")) {
				jSet.MP.musical = Double.parseDouble(data);
			}

		} else if (journal.equals("hr")) {
			if (type.equals("flat")) {
				jSet.HR.flat = Double.parseDouble(data);
			} else if (type.equals("percussive")) {
				jSet.HR.percussive = Double.parseDouble(data);
			} else if (type.equals("undefined")) {
				jSet.HR.undefined = Double.parseDouble(data);
			} else if (type.equals("click/click train")) {
				jSet.HR.click = Double.parseDouble(data);
			} else if (type.equals("non-referential")) {
				jSet.HR.nonref = Double.parseDouble(data);
			} else if (type.equals("soundscapes")) {
				jSet.HR.soundscapes = Double.parseDouble(data);
			} else if (type.equals("musical instrument")) {
				jSet.HR.musical = Double.parseDouble(data);
			}

		}
	}

	public static void main(String[] args) {
		Gson gson = new Gson();

		String fileName = "datav2";
		String filePath = "json/";
		String line = null;

		int[] s0e1 = getYearRange(fileName, filePath);
		int startYear = s0e1[0];
		//int endYear = s0e1[1];

		System.out.println("new JournalSet for: " + startYear);

		// Arbitrary large and small (negative) numbers
		try {

			FileReader fileReader = new FileReader(filePath + fileName + ".csv");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			PrintWriter writer = new PrintWriter(filePath + fileName + ".json", "UTF-8");
			line = null;
			
			/* =======================================================================
			 * 																		*
			 * CONVERSION STARTS HERE												*
			 * 																		*
			 * ======================================================================= */
			
			writer.println("{");
			// line by line to console
			while ((line = bufferedReader.readLine()) != null) {

				String[] lineData = line.split(",");
				String type = lineData[0].toLowerCase();
				String year = lineData[1].toLowerCase();
				String journal = lineData[2].toLowerCase();
				String data = lineData[3].toLowerCase();

				// writer.println("\"" + startYear + "\":");
				System.out.println("Comparing: " + startYear + " to " + year + " & Holding: " + year);
				while (startYear < Integer.parseInt(year)) {
					writer.print("\"" + startYear + "\":");
					writer.println(gson.toJson(jSet) + ",");
					startYear++;
					jSet = new JournalSet();
					System.out.println("new JournalSet for: " + startYear);
				}

				// Insert Data into JournalSet
				if (startYear == Integer.parseInt(year)) {
					System.out.println("OBJECT <<< DATA : " + startYear);
					insertDataIntoJSET(journal, type, data);
				}

				System.out.println("\n");

				// insertDataIntoJSET(journal, type, data);
				// writer.println(gson.toJson(jSet));

			}
			writer.print("\"" + startYear + "\":");
			writer.println(gson.toJson(jSet));
			writer.println("}");
			
			/* =======================================================================
			 * 																		*
			 * CONVERSION ENDS HERE													*
			 * 																		*
			 * ======================================================================= */

			writer.close();
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file " + "'" + fileName + "'");
		}

	}

}
