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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The FileParser class parses the content of a file.
 *
 * @author Cesar Coelho
 */
public class FileParser {

    private final static String SEPARATOR = ",";

    /**
     * The parseFile method parses a file and returns the set of ParsedLines.
     *
     * @param filename The name of the file that has the content
     * @return The parsed Data
     * @throws FileNotFoundException if the file was not found
     * @throws IOException if there is a problem reading the next line of the
     * file
     */
    public static ArrayList<ParsedLine> parseFile(String filename) throws FileNotFoundException, IOException {
        // Open the File and read the content
        File file = new File(filename); // Open the file
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);

        String line = br.readLine(); // Reads the first line!
        ArrayList<ParsedLine> parsedLines = new ArrayList<>();

        for (int i = 1; line != null; i++) {
            String[] splits = line.split(SEPARATOR);
            line = br.readLine(); // Read the next line

            if (splits.length != 2) {
                Logger.getLogger(FileParser.class.getName()).log(Level.WARNING,
                        "Line {0} was ignored because it could not be parsed! "
                        + "(on file: {1})", new Object[]{i, filename});
                continue;
            }

            try {
                // Do the Parsing...
                parsedLines.add(new ParsedLine(splits[0], splits[1]));
            } catch (ParseException ex) {
                Logger.getLogger(FileParser.class.getName()).log(Level.WARNING,
                        "Line {0} was ignored because it could not be parsed! "
                        + "(on file: {1})", new Object[]{i, filename});
            }
        }

        br.close();
        return parsedLines;
    }

}
