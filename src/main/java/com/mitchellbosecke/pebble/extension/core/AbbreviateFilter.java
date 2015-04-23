/*******************************************************************************
 * This file is part of Pebble.
 * 
 * Copyright (c) 2014 by Mitchell Bösecke
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.mitchellbosecke.pebble.extension.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplateImpl;

public class AbbreviateFilter implements Filter {

    private final List<String> argumentNames = new ArrayList<>();

    public AbbreviateFilter() {
        argumentNames.add("length");
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
        String value = (String) input;
        int maxWidth = ((Long) args.get("length")).intValue();

        String ellipsis = "...";
        int length = value.length();

        if (length < maxWidth) {
            return value;
        }
        if (length <= 3) {
            return value;
        }
        return value.substring(0, maxWidth - 3) + ellipsis;
    }

}
