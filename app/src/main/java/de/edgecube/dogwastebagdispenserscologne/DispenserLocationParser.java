package de.edgecube.dogwastebagdispenserscologne;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by boring_wozniak on 12/11/16.
 */

/*
<addresses>
<address>
  <uid>3840</uid>
  <type>Hundekottütenspender</type>
  <name>Hundekottütenspender</name>
  <area>Innenstadt</area>
  <street>Marienplatz nr. 14</street>
  <zipcode>50676</zipcode>
  <city>Köln</city>
  <coordinates>50.933936,6.958532</coordinates>
  <phone>keine Tel. Nr.</phone>
  <openinghours></openinghours>
  <maptitle>Hundekottütenspender</maptitle>
  <locationname><![CDATA[]]></locationname>
  <www><![CDATA[]]></www>
  <district>Altstadt/Süd</district>
  <duedate><![CDATA[]]></duedate>
</address>
</addresses>
 */
public class DispenserLocationParser {
    public List<Dispenser> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(in, null);
            parser.nextTag();

            return readDispenserLocations(parser);
        } finally {
            in.close();
        }
    }

    private List<Dispenser> readDispenserLocations(XmlPullParser parser) throws IOException, XmlPullParserException {
        List<Dispenser> dispensers = new ArrayList<>();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            if (!name.equals("address")) {
                skip(parser);
            }

            dispensers.add(readDispenserLocation(parser));
        }

        return dispensers;
    }

    private Dispenser readDispenserLocation(XmlPullParser parser) throws IOException, XmlPullParserException {
        String id = null;
        String area = null;
        String street = null;
        String zipCode = null;
        String city = null;
        Double lat = Double.NaN;
        Double lon = Double.NaN;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            if (name.equals("uid")) {
                id = readText(parser);
            } else if (name.equals("area")) {
                area = readText(parser);
            } else if (name.equals("street")) {
                street = readText(parser);
            } else if (name.equals("zipcode")) {
                zipCode = readText(parser);
            } else if (name.equals("city")) {
                city = readText(parser);
            } else if (name.equals("coordinates")) {
                String coordinates = readText(parser);
                String[] latAndLon = coordinates.split(",");

                if (latAndLon.length == 2) {
                    lat = Double.parseDouble(latAndLon[0]);
                    lon = Double.parseDouble(latAndLon[1]);
                }
            } else {
                skip(parser);
            }
        }

        return new Dispenser(id, area, street, zipCode, city, lat, lon);
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }

        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
