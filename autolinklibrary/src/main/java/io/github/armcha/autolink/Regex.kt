package io.github.armcha.autolink

import java.util.regex.Pattern

internal val URL_PATTERN = Pattern.compile("(^|[\\s.:;?\\-\\]<\\(])" +
        "((https?://|www\\.|pic\\.)[-\\w;/?:@&=+$\\|\\_.!~*\\|'()\\[\\]%#,☺]+[\\w/#](\\(\\))?)" +
        "(?=$|[\\s',\\|\\(\\).:;?\\-\\[\\]>\\)])")
internal val PHONE_PATTERN: Pattern = Pattern.compile( // sdd = space, dot, or dash
        "(\\+[0-9]+[\\- \\.]*)?" // +<digits><sdd>*
                + "(\\([0-9]+\\)[\\- \\.]*)?" // (<digits>)<sdd>*
                + "([0-9][0-9\\- \\.]+[0-9])") // <digit><digit|sdd>+<digit>
internal val EMAIL_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+")
internal val MENTION_PATTERN: Pattern = Pattern.compile("(?:^|\\s*|$|[.])@[\\p{L}0-9_]+((|[.])([\\p{L}0-9_])+)*([\\p{L}0-9_]+)*")
internal val HASH_TAG_PATTERN: Pattern = Pattern.compile("#[\w_]+")
