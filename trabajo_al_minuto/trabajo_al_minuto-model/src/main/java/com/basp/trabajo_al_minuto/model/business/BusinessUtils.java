/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.model.business;

import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import com.basp.trabajo_al_minuto.model.dto.EmailMessage;
import com.basp.trabajo_al_minuto.model.dto.RollbackResult;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;
import com.google.gson.Gson;


/**
 *
 * @author Bryan Silva
 */
public class BusinessUtils implements Serializable {

    private static MimeMessage message;
    private static BodyPart bodyPart;
    private static Multipart multipart;
    private static DataSource dataSource;
    private static InputStream inStream;
    private static OutputStream outStream;

    private static final long serialVersionUID = 1L;

    public static Properties propertiesFile(String pathFile) throws BusinessException {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(pathFile);
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (FileNotFoundException ex) {
            throw new BusinessException(ex);
        } catch (IOException ex) {
            throw new BusinessException(ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(BusinessUtils.class.getName()).log(Level.SEVERE, "BusinessUtils.propertiesFile Exception", ex);
            }
        }
    }

    public static int executeCommandLine(String file, String dir) throws BusinessException {
        try {
            Process process = Runtime.getRuntime().exec(file, null, new File(dir));
            process.waitFor();
            return process.exitValue();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(BusinessUtils.class.getName()).log(Level.SEVERE, "BusinessUtils.executeCommandLine Exception", ex);
            throw new BusinessException(ex);
        }
    }

    public static InputSource characterStreamString(StringReader reader) throws BusinessException {
        InputSource archivo = new InputSource();
        archivo.setCharacterStream(reader);
        return archivo;
    }

    public static Document normalizeDocument(InputSource inputSource) throws BusinessException {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(inputSource);
            document.getDocumentElement().normalize();
            return document;
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(BusinessUtils.class.getName()).log(Level.SEVERE, "BusinessUtils.normalizeDocument Exception", ex);
            throw new BusinessException(ex);
        }
    }

    public static void writeLog(String file, List<LogRecord> records) throws BusinessException {
        try {
            Logger logger = Logger.getAnonymousLogger();
            SimpleFormatter formatter = new SimpleFormatter();
            FileHandler fh = new FileHandler(file, Boolean.TRUE);
            fh.setFormatter(formatter);
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
            records.stream().forEach((logRecord) -> {
                logger.log(logRecord);
            });
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(BusinessUtils.class.getName()).log(Level.SEVERE, "BusinessUtils.writeLog Exception", ex);
            throw new BusinessException(ex);
        }
    }

    public static void sendMassEmail(List<EmailMessage> emdtos) throws BusinessException {
        for (EmailMessage emdto : emdtos) {
            sendEmail(emdto);
        }
        System.out.println("Send Mass Email Sucessfully!");
    }

    public static Boolean sendEmail(final EmailMessage emdto) throws BusinessException {
        try {
            Session session;
            Properties properties = System.getProperties();
            properties.put("mail.host", emdto.getHost());
            properties.put("mail.transport.protocol", "smtp");
            properties.put("mail.smtp.port", emdto.getPort());
            properties.put("mail.smtp.starttls.enable", emdto.getStarttls());
            if (emdto.getUser() != null && emdto.getPassword() != null) {
                properties.put("mail.smtp.auth", "true");
                session = Session.getDefaultInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                emdto.getUser(), emdto.getPassword());
                    }
                });
            } else {
                properties.put("mail.smtp.auth", "false");
                session = Session.getDefaultInstance(properties);
            }

            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emdto.getFrom(), emdto.getMask()));
            message.setSubject(emdto.getSubject());

            for (String to : emdto.getToList()) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }
            if (emdto.getCcList() != null) {
                for (String cc : emdto.getCcList()) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
                }
            }
            if (emdto.getCcoList() != null) {
                for (String cco : emdto.getCcoList()) {
                    message.addRecipient(Message.RecipientType.BCC, new InternetAddress(cco));
                }
            }

            bodyPart = new MimeBodyPart();
            bodyPart.setContent(emdto.getBodyMessage(), emdto.getMimeTypeMessage());
            multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);

            if (emdto.getFilePaths() != null) {
                for (String filePath : emdto.getFilePaths()) {
                    File file = new File(filePath);
                    if (file.exists()) {
                        dataSource = new FileDataSource(file);
                        bodyPart = new MimeBodyPart();
                        bodyPart.setDataHandler(new DataHandler(dataSource));
                        bodyPart.setFileName(file.getName());
                        multipart.addBodyPart(bodyPart);
                    }
                }
            }

            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException ex) {
            Logger.getLogger(BusinessUtils.class.getName()).log(Level.SEVERE, "BusinessUtils.sendEmail Exception", ex);
            throw new BusinessException(ex);
        }
        return Boolean.TRUE;
    }

    public static JasperPrint reportGenerate(Map param, String file, Connection conn) throws BusinessException {
        try {
            JasperReport report = JasperCompileManager.compileReport(file);
            return JasperFillManager.fillReport(report, param, conn);
        } catch (JRException ex) {
            Logger.getLogger(BusinessUtils.class.getName()).log(Level.SEVERE, "BusinessUtils.reportGenerate Exception", ex);
            throw new BusinessException(ex);
        }
    }

    public static byte[] reportExport(JasperPrint jasperPrint) throws BusinessException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JRXlsxExporter rxae = new JRXlsxExporter();
            rxae.setExporterInput(new SimpleExporterInput(jasperPrint));
            rxae.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(false);
            configuration.setDetectCellType(Boolean.TRUE);
            configuration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
            configuration.setWhitePageBackground(Boolean.FALSE);
            rxae.setConfiguration(configuration);
            rxae.exportReport();
            return baos.toByteArray();
        } catch (JRException ex) {
            Logger.getLogger(BusinessUtils.class.getName()).log(Level.SEVERE, "BusinessUtils.reportExport Exception", ex);
            throw new BusinessException(ex);
        }
    }

    public static LDAPConnection ldapConnection(String host, Integer port) throws BusinessException {
        try {
            LDAPConnection connection = new LDAPConnection();
            connection.connect(host, port);
            return connection;
        } catch (LDAPException ex) {
            Logger.getLogger(BusinessUtils.class.getName()).log(Level.SEVERE, "BusinessUtils.ldapConnection Exception", ex);
            throw new BusinessException(ex);
        }
    }

    public static LDAPSearchResults ldapSearchResults(LDAPConnection ldapConnection, String base, Integer scope, String filter, String[] attr) throws BusinessException {
        try {
            return ldapConnection.search(base, scope, filter, attr, Boolean.TRUE);
        } catch (LDAPException ex) {
            Logger.getLogger(BusinessUtils.class.getName()).log(Level.SEVERE, "BusinessUtils.ldapEntryResult Exception", ex);
            throw new BusinessException(ex);
        }
    }

    public static LDAPEntry ldapEntryResult(LDAPSearchResults results) throws BusinessException {
        try {
            return results.next();
        } catch (LDAPException ex) {
            Logger.getLogger(BusinessUtils.class.getName()).log(Level.SEVERE, "BusinessUtils.ldapEntryResult Exception", ex);
            throw new BusinessException(ex);
        }
    }

    public static Map<String, Object> toMap(JSONObject object) throws BusinessException {
        Map<String, Object> map = new HashMap();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws BusinessException {
        List<Object> list = new ArrayList();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    public static LocalDate toLocalDate(Date date) throws BusinessException {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date toDate(LocalDate localDate) throws BusinessException {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(LocalDateTime localDateTime) throws BusinessException {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String rollbackValidation(ConstraintViolationException cve) {
        Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
            List<RollbackResult> rlb = new ArrayList();
            while (iterator.hasNext()) {
                ConstraintViolation<?> cv = iterator.next();
                rlb.add(new RollbackResult(cv.getRootBean().getClass().getCanonicalName(), cv.getMessage(), cv.getInvalidValue().toString()));
            }
            return new Gson().toJson(rlb);
        }
        return null;
    }
}
