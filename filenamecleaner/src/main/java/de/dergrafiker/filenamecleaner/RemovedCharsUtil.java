package de.dergrafiker.filenamecleaner;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RemovedCharsUtil {
    private static final Set<Character> IGNORED = fill();

    private static Set<Character> fill() {
        Set<Character> ignored = new HashSet<>();
//        Collections.addAll(ignored, '[', ']', '{', '}', '(', ')', ',', ';', ' ', '.', '&', '+', '#', '$', '!', '\'',
//                           '´', 'ä', 'ü', 'ö', 'ß', '@', '_', '∞', '`', '=' ,'-', '\u2010', '’');
        return ignored;
    }

    Set<Character> getRemovedChars(String oldName, String cleaned) {
        Set<Character> removedChars = getUniqueLowerCaseChars(oldName);
        removedChars.removeAll(getUniqueLowerCaseChars(cleaned));
        removedChars.removeAll(IGNORED);
        return removedChars;
    }

    private Set<Character> getUniqueLowerCaseChars(String oldName) {
        Stream<Character> characterStream = oldName.chars()
                .mapToObj(c -> (char) Character.toLowerCase(c));

        return characterStream.collect(Collectors.toSet());
    }
}
