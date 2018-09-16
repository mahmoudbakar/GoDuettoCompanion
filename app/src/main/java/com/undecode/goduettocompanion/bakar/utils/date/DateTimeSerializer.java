package com.undecode.goduettocompanion.bakar.utils.date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Type;
import java.util.Locale;

/**
 * Created by Amr on 3/18/2017.
 */

public class DateTimeSerializer implements JsonSerializer<DateTime> {
    private Boolean toDotNetFormat;

    public DateTimeSerializer() {
    }

    public DateTimeSerializer(Boolean toDotNetFormat) {
        this.toDotNetFormat = toDotNetFormat;
    }

    @Override
    public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
        if (src.getZone() != DateTimeZone.UTC) {
            int offset = src.getZone().getOffsetFromLocal(src.getMillis());
            src = src.toDateTime(DateTimeZone.UTC).plus(offset);
        }
        return toDotNetFormat ?
                new JsonPrimitive(String.format(Locale.ENGLISH, "/Date(%d)/", src.getMillis())) :
                new JsonPrimitive(src.toString(ISODateTimeFormat.dateTime()));
    }
}
