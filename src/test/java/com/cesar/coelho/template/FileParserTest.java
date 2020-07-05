/*
 * Copyright (c) 2020 Cesar Coelho
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package com.cesar.coelho.template;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the FileParser class.
 * 
 * @author Cesar Coelho
 */
public class FileParserTest {

    @Test
    public void testDefaultFilename() {
        String filename = "test_file.txt";
        System.out.println("Testing " + filename + " file... (requirement 1)");

        ArrayList<ParsedLine> periods = null;
        try {
            periods = FileParser.parseFile(filename);
        } catch (IOException ex) {
            Logger.getLogger(FileParserTest.class.getName()).log(Level.SEVERE,
                    "The file could not be parsed!", ex);
        }

        assertNotEquals(null, periods);
    }

    @Test(expected = FileNotFoundException.class)
    public void testInexistentFilename() throws IOException {
        String filename = "inexistent_filename";
        System.out.println("Testing " + filename + " file...");
        ArrayList<ParsedLine> parsedLines = FileParser.parseFile(filename);
    }

}
