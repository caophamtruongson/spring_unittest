package spring_unittest.test_data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractData<T> {

  private BufferedReader br = null;
  private String line = "";
  private String cvsSplitBy = ",";
  private String[] titles;
  protected List<T> objects;

  public AbstractData() {
    br = null;
    line = "";
    cvsSplitBy = ",";
    titles = null;
    objects = new ArrayList<T>();
  }

  public void generateData(String fileName) {
    ClassLoader classLoader = getClass().getClassLoader();

    try {
      br = new BufferedReader(new FileReader(classLoader.getResource(fileName).getFile()));
      line = br.readLine();
      if (line == null) {
        return;
      }
      titles = line.split(cvsSplitBy);

      while ((line = br.readLine()) != null) {
        addObject(line.split(cvsSplitBy));
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public String[] getTitles() {
    return titles;
  }

  public List<T> getObjects() {
    return objects;
  }

  public void clearObject() {
    objects.clear();
  }

  public void addObject(String[] object) {};
}
