/***************************************************************************
 * Copyright 2018 Global Biodiversity Information Facility Secretariat
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ***************************************************************************/

package org.gbif.utils.file.spreadsheet;

import org.gbif.utils.file.FileUtils;

import java.io.File;
import java.io.FileWriter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static junit.framework.TestCase.assertEquals;
import static org.apache.commons.io.FileUtils.contentEqualsIgnoreEOL;
import static org.junit.Assert.assertTrue;

/**
 * {@link ExcelConverter} related tests
 */
public class ExcelConverterTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  private static final String CSV_TEST_FILE_LOCATION = "spreadsheet/occurrence-workbook.csv";
  private static final String XLSX_TEST_FILE_LOCATION = "spreadsheet/occurrence-workbook.xlsx";
  private static final String XLS_TEST_FILE_LOCATION = "spreadsheet/occurrence-workbook.xls";

  private final File testCsvFile = FileUtils.getClasspathFile(CSV_TEST_FILE_LOCATION);
  private final File testXlsFile = FileUtils.getClasspathFile(XLS_TEST_FILE_LOCATION);
  private final File testXlsxFile = FileUtils.getClasspathFile(XLSX_TEST_FILE_LOCATION);

  @Test
  public void testExcelConverter() throws Exception {
    File testFile = folder.newFile();
    //testFile = new File("/tmp/test.csv");
    //System.out.println(testFile.toPath());

    long lines = ExcelConverter.convert(testXlsFile.toPath(),
      new CsvSpreadsheetConsumer(new FileWriter(testFile)));

    assertTrue(contentEqualsIgnoreEOL(testFile, testCsvFile, "UTF-8"));
    assertEquals(7L, lines);
  }

  @Test
  public void testExcelXmlConverter() throws Exception {
    File testFile = folder.newFile();
    //testFile = new File("/tmp/test.csv");
    //System.out.println(testFile.toPath());

    long lines = ExcelXmlConverter.convert(testXlsxFile.toPath(),
      new CsvSpreadsheetConsumer(new FileWriter(testFile)));

    assertTrue(contentEqualsIgnoreEOL(testFile, testCsvFile, "UTF-8"));
    assertEquals(7L, lines);
  }

  @Test
  public void testExcelXmlConverterBlankLines() throws Exception {
    File testFile = folder.newFile();
    //testFile = new File("/tmp/test.csv");
    //System.out.println(testFile.toPath());

    long lines = ExcelXmlConverter.convert(FileUtils.getClasspathFile("spreadsheet/blank-rows.xlsx").toPath(),
      new CsvSpreadsheetConsumer(new FileWriter(testFile)));

    assertTrue(contentEqualsIgnoreEOL(testFile, FileUtils.getClasspathFile("spreadsheet/blank-rows.csv"), "UTF-8"));
    assertEquals(1_048_576L, lines);
  }
}
