package utils;

import com.ubb.cms.Edition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Marius Adam
 */
public class DateUtils {
    private static final String                        DateOnlyFormat = "yyyy-MM-dd";
    private static       Map<String, SimpleDateFormat> formatMap      = new HashMap<>();

    public static Date asDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }

        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }

        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        if (date == null) {
            return null;
        }

        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }

        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * @param tClass       The object class type
     * @param propertyName The name of the date property which will be formatted
     * @param dateFormat   A valid date format. Eg. "yyyy-MM-dd"
     * @param <S>          The type parameter
     * @return The callback which can resolve the property
     */
    public static <S> Callback<TableColumn.CellDataFeatures<S, String>, ObservableValue<String>> dateAsStringValueFactory(
            Class<S> tClass, String propertyName, String dateFormat
    ) {
        Method propertyGetter;
        try {
            propertyGetter = new PropertyDescriptor(propertyName, tClass).getReadMethod();
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }

        return param -> {
            SimpleStringProperty observableProperty = new SimpleStringProperty();
            try {
                observableProperty.setValue(getSimpleDateFormat(dateFormat).format(propertyGetter.invoke(param.getValue())));
                return observableProperty;
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Store an object for each format type
     *
     * @param format The date format as string
     * @return The SimpleDateFormat object for the given format
     */
    private synchronized static SimpleDateFormat getSimpleDateFormat(String format) {
        formatMap.computeIfAbsent(format, SimpleDateFormat::new);
        return formatMap.get(format);
    }

    public static Callback<TableColumn.CellDataFeatures<Edition, String>, ObservableValue<String>> dateAsStringValueFactory(
            String propertyName
    ) {
        return dateAsStringValueFactory(Edition.class, propertyName, DateOnlyFormat);
    }
}