import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Rank {
	private ArrayList<String> people;

	public Rank(String name, int step) throws IOException {
		people = new ArrayList<>();
		readFileByLines("./res/rank.txt");
		people.add(name + " " + step);
		Person[] people = new Person[this.people.size()];
		for (int i = 0; i < people.length; i++) {
			people[i] = new Person(this.people.get(i));
		}
		quickSort(people, 0, people.length - 1);
		writeFile(people);
	}

	public void writeFile(Person[] people) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		for (Person person : people) {
			stringBuilder.append(person.toString()).append("\n");
		}
		Files.write(Paths.get("./res/rank.txt"), stringBuilder.toString().getBytes());
	}

	public static ArrayList<String> topTen(){
		ArrayList<String> str = new ArrayList<>();
		File file = new File("./res/rank.txt");
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String tempString;
			while ((tempString = reader.readLine()) != null) {
				str.add(tempString);
				if (str.size() == 10)
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}


	public void readFileByLines(String fileName) {
		File file = new File(fileName);
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String tempString;
			while ((tempString = reader.readLine()) != null) {
				people.add(tempString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void quickSort(Person[] f, int left, int right) {
		int i = left;
		int j = right;
		int index = f[i].getStep();
		while (i < j) {
			while (i < j && f[j].getStep() >= index)
				j--;
			if (f[j].getStep() <= index) {
				int temp = f[j].getStep();
				f[j].setStep(f[i].getStep());
				f[i].setStep(temp);
				String temp1 = f[j].getName();
				f[j].setName(f[i].getName());
				f[i].setName(temp1);
			}
			while (i < j && f[i].getStep() <= index)
				i++;
			if (f[i].getStep() >= index) {
				int temp = f[j].getStep();
				f[j].setStep(f[i].getStep());
				f[i].setStep(temp);
				String temp1 = f[j].getName();
				f[j].setName(f[i].getName());
				f[i].setName(temp1);
			}
		}
		if (i > left)
			quickSort(f, left, i - 1);
		if (j < right)
			quickSort(f, i + 1, right);
	}
}

class Person {
	private String name;
	private int step;

	public Person(String data) {
		String[] a = data.split(" ");
		name = a[0];
		step = Integer.parseInt(a[1]);
	}

	public String getName() {
		return name;
	}

	public int getStep() {
		return step;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String toString() {
		return name + " " + step;
	}
}