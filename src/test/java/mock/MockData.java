package mock;

import dev.chargedbyte.wim.model.LegacyAvailability;
import dev.chargedbyte.wim.model.LegacyProduct;

import java.util.Arrays;
import java.util.List;

public class MockData {
    public static class LegacyProducts {
        public static final LegacyProduct[] beanies = {
            new LegacyProduct("R4OuKi9VnVwMKy321ikA", "beanies", "habitasse platea", Arrays.asList("Khaki", "Fuscia"), 638, "nascetur"),
            new LegacyProduct("6rCPg5CcqFJcpAWZDXU0", "beanies", "mattis egestas", Arrays.asList("Pink", "Fuscia", "Crimson"), 614, "aenean"),
            new LegacyProduct("GgFnGfzmkZatPp6Sm17p", "beanies", "mauris eget massa", List.of("Purple"), 305, "tincidunt"),
            new LegacyProduct("chUDARuvQnB021wTfEeO", "beanies", "libero quis", Arrays.asList("Puce", "Puce"), 528, "tortor"),
            new LegacyProduct("DenjHhqoM2UXePNNY0y1", "beanies", "turpis elementum ligula", List.of("Khaki"), 426, "justo"),
            new LegacyProduct("1XexnO7fo9opvgtCWAFE", "beanies", "nam dui", List.of("Teal"), 161, "ullamcorper"),
            new LegacyProduct("TzpkfyvDkthZmmgq62S6", "beanies", "diam erat", Arrays.asList("Puce", "Teal"), 585, "interdum"),
            new LegacyProduct("iMmLiRxw6tQ3eGeCO9I0", "beanies", "pede posuere", Arrays.asList("Mauv", "Yellow"), 540, "orci"),
            new LegacyProduct("R00IGqYRYA6OEJyzmQOh", "beanies", "volutpat in congue", Arrays.asList("Red", "Yellow", "Violet"), 148, "nonummy"),
            new LegacyProduct("r5fD2JsKobkwAOQ7yMLX", "beanies", "ut erat", Arrays.asList("Orange", "Violet", "Indigo"), 122, "condimentum")
        };

        public static final LegacyProduct[] facemasks = {
            new LegacyProduct("nHqm5T0en3sLPIJRNlJ6", "facemasks", "justo maecenas rhoncus", Arrays.asList("Pink", "Blue"), 530, "donec"),
            new LegacyProduct("jyhExPtx33ydR5FiMWOY", "facemasks", "est quam pharetra", List.of("Puce"), 127, "praesent"),
            new LegacyProduct("IZYb5mL3eZVll4iPFEde", "facemasks", "donec ut", List.of("Violet"), 547, "praesent"),
            new LegacyProduct("dUOP1eicICrMCMCyd6hl", "facemasks", "aenean fermentum", Arrays.asList("Puce", "Yellow"), 345, "luctus"),
            new LegacyProduct("qfzVoDwwmluXicU1y6fI", "facemasks", "id pretium iaculis", Arrays.asList("Violet", "Red", "Goldenrod"), 591, "vulputate"),
            new LegacyProduct("ndAm22feOzop6nwhrIo4", "facemasks", "volutpat in congue", Arrays.asList("Mauv", "Orange"), 457, "a"),
            new LegacyProduct("G7jzohjttBIgvRyFdEJb", "facemasks", "ut suscipit", Arrays.asList("Maroon", "Maroon", "Khaki"), 282, "nulla"),
            new LegacyProduct("bcH12ns5CRqCDpJMISiW", "facemasks", "sed accumsan felis", List.of("Fuscia"), 28, "nisi"),
            new LegacyProduct("2TcocNl12AQ5pNi9tZFq", "facemasks", "velit donec diam", Arrays.asList("Green", "Green", "Green"), 178, "sapien"),
            new LegacyProduct("ypZ6BX71YRRGcHpu8S0D", "facemasks", "diam id", Arrays.asList("Turquoise", "Red", "Green"), 348, "vestibulum"),
        };

        public static final LegacyProduct[] gloves = {
            new LegacyProduct("T6Ak46JHX9gt5UndG9KR", "gloves", "morbi quis tortor", Arrays.asList("Fuscia", "Orange", "Violet"), 673, "eget"),
            new LegacyProduct("8GDB8vrt8RN1APWzIxmo", "gloves", "vivamus tortor", List.of("Violet"), 459, "feugiat"),
            new LegacyProduct("8D1a1gXfu8fBd1tkNnDS", "gloves", "in hac", Arrays.asList("Crimson", "Pink", "Red"), 491, "in"),
            new LegacyProduct("PETAQXFOQdwvOLWrx66O", "gloves", "urna pretium nisl", Arrays.asList("Pink", "Yellow"), 657, "lectus"),
            new LegacyProduct("KnpHSZI1qOnOQDQvaSn9", "gloves", "porttitor lacus", Arrays.asList("Mauv", "Crimson", "Pink"), 172, "praesent"),
            new LegacyProduct("O6cT8Jcrh57oWygXC02C", "gloves", "vivamus metus", Arrays.asList("Mauv", "Aquamarine", "Turquoise"), 417, "orci"),
            new LegacyProduct("hC7wz59gTw9ZPcCTgTpZ", "gloves", "interdum eu", List.of("Purple"), 319, "tellus"),
            new LegacyProduct("u19XMwOo1mvn0ERoGPUd", "gloves", "cubilia curae", Arrays.asList("Yellow", "Mauv", "Violet"), 193, "pede"),
            new LegacyProduct("aCacNB4yqHD3lgwmF7T3", "gloves", "arcu libero rutrum", Arrays.asList("Crimson", "Aquamarine"), 98, "varius"),
            new LegacyProduct("XtZSNh7TN9sIIvRPhG1Y", "gloves", "donec semper sapien", List.of("Pink"), 550, "mattis"),
        };
    }

    public static class LegacyAvailabilities {
        public static final LegacyAvailability[] nascetur = {
            new LegacyAvailability("R4OuKi9VnVwMKy321ikA", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>INSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] aenean = {
            new LegacyAvailability("6rCPg5CcqFJcpAWZDXU0", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>INSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] tincidunt = {
            new LegacyAvailability("GgFnGfzmkZatPp6Sm17p", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>OUTOFSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] tortor = {
            new LegacyAvailability("chUDARuvQnB021wTfEeO", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>LESSTHAN10</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] justo = {
            new LegacyAvailability("DenjHhqoM2UXePNNY0y1", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>INSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] ullamcorper = {
            new LegacyAvailability("1XexnO7fo9opvgtCWAFE", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>OUTOFSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] interdum = {
            new LegacyAvailability("TzpkfyvDkthZmmgq62S6", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>INSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] orci = {
            new LegacyAvailability("iMmLiRxw6tQ3eGeCO9I0", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>OUTOFSTOCK</INSTOCKVALUE>\n</AVAILABILITY>"),
            new LegacyAvailability("O6cT8Jcrh57oWygXC02C", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>LESSTHAN10</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] nonummy = {
            new LegacyAvailability("R00IGqYRYA6OEJyzmQOh", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>INSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] condimentum = {
            new LegacyAvailability("r5fD2JsKobkwAOQ7yMLX", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>OUTOFSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static LegacyAvailability[] donec = {
            new LegacyAvailability("nHqm5T0en3sLPIJRNlJ6", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>LESSTHAN10</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static LegacyAvailability[] praesent = {
            new LegacyAvailability("jyhExPtx33ydR5FiMWOY", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>OUTOFSTOCK</INSTOCKVALUE>\n</AVAILABILITY>"),
            new LegacyAvailability("IZYb5mL3eZVll4iPFEde", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>LESSTHAN10</INSTOCKVALUE>\n</AVAILABILITY>"),
            new LegacyAvailability("KnpHSZI1qOnOQDQvaSn9", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>INSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] luctus = {
            new LegacyAvailability("dUOP1eicICrMCMCyd6hl", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>INSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] vulputate = {
            new LegacyAvailability("qfzVoDwwmluXicU1y6fI", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>LESSTHAN10</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] a = {
            new LegacyAvailability("ndAm22feOzop6nwhrIo4", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>LESSTHAN10</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] nulla = {
            new LegacyAvailability("G7jzohjttBIgvRyFdEJb", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>LESSTHAN10</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] nisi = {
            new LegacyAvailability("bcH12ns5CRqCDpJMISiW", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>LESSTHAN10</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] sapien = {
            new LegacyAvailability("2TcocNl12AQ5pNi9tZFq", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>INSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] vestibulum = {
            new LegacyAvailability("ypZ6BX71YRRGcHpu8S0D", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>LESSTHAN10</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] eget = {
            new LegacyAvailability("T6Ak46JHX9gt5UndG9KR", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>OUTOFSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] feugiat = {
            new LegacyAvailability("8GDB8vrt8RN1APWzIxmo", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>OUTOFSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] in = {
            new LegacyAvailability("8D1a1gXfu8fBd1tkNnDS", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>LESSTHAN10</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] lectus = {
            new LegacyAvailability("PETAQXFOQdwvOLWrx66O", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>LESSTHAN10</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] tellus = {
            new LegacyAvailability("hC7wz59gTw9ZPcCTgTpZ", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>LESSTHAN10</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] pede = {
            new LegacyAvailability("u19XMwOo1mvn0ERoGPUd", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>INSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] varius = {
            new LegacyAvailability("aCacNB4yqHD3lgwmF7T3", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>OUTOFSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };

        public static final LegacyAvailability[] mattis = {
            new LegacyAvailability("XtZSNh7TN9sIIvRPhG1Y", "<AVAILABILITY>\n  <CODE>200</CODE>\n  <INSTOCKVALUE>INSTOCK</INSTOCKVALUE>\n</AVAILABILITY>")
        };
    }
}
