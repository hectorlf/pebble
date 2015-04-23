/*******************************************************************************
 * This file is part of Pebble.
 * 
 * Copyright (c) 2014 by Mitchell Bösecke
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.mitchellbosecke.pebble.extension.escaper;

import java.util.List;
import java.util.Map;

import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplateImpl;

public class RawFilter implements Filter {

    public List<String> getArgumentNames() {
        return null;
    }

    public Object apply(Object inputObject, Map<String, Object> args, EvaluationContext context, PebbleTemplateImpl self) {
        /*
         * Doesn't actually do anything to the input. This filter's existence is
         * enough for the EscaperNodeVisitor to know not to escape this
         * expression.
         */
        return inputObject;
    }

}
