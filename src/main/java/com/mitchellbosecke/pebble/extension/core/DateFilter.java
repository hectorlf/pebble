/*******************************************************************************
 * This file is part of Pebble.
 * 
 * Copyright (c) 2014 by Mitchell Bösecke
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.mitchellbosecke.pebble.extension.core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplateImpl;

public class DateFilter implements Filter {

    private final List<String> argumentNames = new ArrayList<>();

    public DateFilter() {
        argumentNames.add("format");
        argumentNames.add("existingFormat");
    }

    @Override
    public List<String> getArgumentNames() {
        return argumentNames;
    }

    @Override
    public Object apply(Object input, Map<String, Object> args, EvaluationContext context, PebbleTemplateImpl self) {
        if (input == null) {
            return null;
        }
        Date date = null;

        DateFormat existingFormat = null;
        DateFormat intendedFormat = null;

        Locale locale = context.getLocale();

        intendedFormat = new SimpleDateFormat((String) args.get("format"), locale);

        if (args.get("existingFormat") != null) {
            existingFormat = new SimpleDateFormat((String) args.get("existingFormat"), locale);
            try {
                date = existingFormat.parse((String) input);
            } catch (ParseException e) {
                throw new RuntimeException("Could not parse date", e);
            }
        } else {
            date = (Date) input;
        }

        return intendedFormat.format(date);
    }
}
