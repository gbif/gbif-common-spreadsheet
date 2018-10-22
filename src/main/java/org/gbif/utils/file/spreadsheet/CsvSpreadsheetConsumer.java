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

import org.gbif.common.shaded.com.fasterxml.jackson.databind.SequenceWriter;
import org.gbif.common.shaded.com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.gbif.common.shaded.com.fasterxml.jackson.dataformat.csv.CsvParser;
import org.gbif.common.shaded.com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Convert rows from a spreadsheet into CSV.
 */
public class CsvSpreadsheetConsumer implements SpreadsheetConsumer {
  final SequenceWriter sequenceWriter;
  long count = 0;

  public CsvSpreadsheetConsumer(Writer writer) throws IOException {
    CsvMapper mapper = new CsvMapper();
    mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);

    CsvSchema schema = CsvSchema.emptySchema().withColumnSeparator(',').withLineSeparator("\n").withQuoteChar('"');

    sequenceWriter = mapper.writerFor(List.class).with(schema).writeValues(writer);
  }

  @Override
  public void write(List<String> row) throws IOException {
    sequenceWriter.write(row);
    count++;
  }

  @Override
  public void flush() throws IOException {
    sequenceWriter.flush();
  }

  @Override
  public long getCount() {
    return count;
  }
}
