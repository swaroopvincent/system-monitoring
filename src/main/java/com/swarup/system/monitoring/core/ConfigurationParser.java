package com.swarup.system.monitoring.core;

import com.swarup.system.monitoring.exceptions.ConfigurationXMLParserException;
import com.swarup.system.monitoring.model.Configuration;
import com.swarup.system.monitoring.model.Machine;
import com.swarup.system.monitoring.model.Metric;
import com.swarup.system.monitoring.model.Parser;
import static com.swarup.system.monitoring.util.Constants.METRIC_ELEMENT;
import static com.swarup.system.monitoring.util.Constants.MACHINE_ELEMENT;
import static com.swarup.system.monitoring.util.Constants.COLUMN_ATTRIBUTE;
import static com.swarup.system.monitoring.util.Constants.ROW_ATTRIBUTE;
import static com.swarup.system.monitoring.util.Constants.PARSER_SELECTION_ELEMENT;
import static com.swarup.system.monitoring.util.Constants.NAME_ATTRIBUTE;
import static com.swarup.system.monitoring.util.Constants.EXECUTE_ATTRIBUTE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eswavin on 6/25/16.
 * This class build the internal models for the application to use.
 */
public class ConfigurationParser {

    private static Logger logger = LoggerFactory.getLogger(ConfigurationParser.class);

    private String configFileName;
    private Document document;

    public ConfigurationParser(final String configFileName) {
        this.configFileName = configFileName;
    }

    /**
     * parse the configuration xml file and build the Configuration model.
     * @return {@code Configuration} the configuration model
     */
    public Configuration parseAndBuildModel() {
        boolean isParsingFine = parseFile();
        if(isParsingFine) {
            return buildModel();
        } else {
            throw new ConfigurationXMLParserException(configFileName);
        }
    }

    private boolean parseFile() {
        try {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream(configFileName);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(stream);
        } catch (Exception e) {
            logger.error("Failed parsing the file {}", configFileName);
            return false;
        }
        return true;
    }

    private Configuration buildModel() {
        Element configElement = document.getDocumentElement();
        Configuration configuration = new Configuration();
        configuration.setMachine(getMachine(configElement));
        configuration.setMetrics(getMetrics(configElement));
        return configuration;
    }

    private List<Metric> getMetrics(final Element configElement) {
        List<Metric> metrics = new ArrayList<Metric>();
        NodeList metricsNodes = configElement.getElementsByTagName(METRIC_ELEMENT);
        for (int i = 0; i < metricsNodes.getLength(); i++) {
            Element metricElement = (Element) metricsNodes.item(i);
            Metric metric = new Metric();
            metric.setName(metricElement.getAttribute(NAME_ATTRIBUTE));
            metric.setCommand(metricElement.getAttribute(EXECUTE_ATTRIBUTE));
            metric.setParser(getParser(metricElement));
            metrics.add(metric);
        }
        return metrics;
    }

    private Machine getMachine(final Element configElement) {
        Element machineElement = (Element) configElement.getElementsByTagName(MACHINE_ELEMENT).item(0);
        Parser parser = getParser(machineElement);
        Machine machine = new Machine();
        machine.setCommand(machineElement.getAttribute(EXECUTE_ATTRIBUTE));
        machine.setParser(parser);
        return machine;
    }

    private Parser getParser(Element machineElement) {
        Element parserElementForMachine = (Element) machineElement.getElementsByTagName(PARSER_SELECTION_ELEMENT).item(0);
        Parser parser = new Parser();
        parser.setRow(Integer.parseInt(parserElementForMachine.getAttribute(ROW_ATTRIBUTE)));
        parser.setColumn(Integer.parseInt(parserElementForMachine.getAttribute(COLUMN_ATTRIBUTE)));
        return parser;
    }

}
