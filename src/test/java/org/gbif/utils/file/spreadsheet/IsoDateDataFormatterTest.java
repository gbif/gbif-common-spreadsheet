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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IsoDateDataFormatterTest {

  @Test
  public void testIsoDateDateFormatter() {
    IsoDateDataFormatter iddf = new IsoDateDataFormatter();

    assertEquals("1990-01-02", iddf.formatRawCellContents(32875.0, 0, "mm/dd/yy"));
    assertEquals("1990-01-05T17:00:00Z", iddf.formatRawCellContents(32878.708333333336, 0, "hh:mm:ss mm/dd/yy"));
  }
}
