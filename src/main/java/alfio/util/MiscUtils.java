/**
 * This file is part of alf.io.
 *
 * alf.io is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * alf.io is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with alf.io.  If not, see <http://www.gnu.org/licenses/>.
 */
package alfio.util;

import java.util.List;

public class MiscUtils {
    private MiscUtils() {
    }

    public static <T> T getAtIndexOrNull(List<T> elements, int index) {
        if (elements == null || index < 0 || index >= elements.size()) {
            return null;
        }
        return elements.get(index);
    }

    public static String removeTabsAndNewlines(String input) {
        return input != null ? input.replaceAll("[\n\r]", "_") : "";
    }

}
