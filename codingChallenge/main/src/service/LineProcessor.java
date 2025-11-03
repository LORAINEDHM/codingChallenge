package service;

import model.Event;
import model.EventTypeEnum;
import model.User;
import utils.DateUtils;

import java.util.UUID;

public class LineProcessor {

    public LineProcessor() {};

    public Event parseLine(String line) {
        try {
            String[] parts = this.parseColumns(line);
            return this.parseContent(parts);
        } catch(IllegalArgumentException e) {
            throw e;
        }
    }

    private String[] parseColumns(String line) {
        String[] parts = line.split(";");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid line.");
        }
        return parts;
    }

    private Event parseContent(String[] parts) {
            EventTypeEnum eventType = EventTypeEnum.fromValue(parts[0]);
            User user = this.parsePayload(parts[1]);
            return new Event(eventType, user);
    }

    private User parsePayload(String json) {
        json.trim();
        json = this.removeBraces(json);
        String[] pairs = json.split(",");
        User user = new User();
        for (String pair: pairs) {
            String[] kv = pair.split(":", 2);
            String key = kv[0].trim().replace("\"", "");
            String value = kv[1].trim().replace("\"", "");
            if (key.isEmpty() || value.isEmpty()) {
                throw new IllegalArgumentException("Invalid line.");
            }
            switch (key.toLowerCase()) {
                case "id":
                    user.setId(UUID.fromString(value));
                    break;
                case "firstname":
                    user.setFirstname(value);
                    break;
                case "surname":
                    user.setSurname(value);
                    break;
                case "email":
                    user.setEmail(value);
                    break;
                case "birthdate":
                    user.setBirthdate(DateUtils.stringToDate(value));
                    break;
                case "city":
                    user.setCity(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown attribute: '" + key + "'.");
            }
        }
        return user;
    }


    private String removeBraces(String json) {
        if (json.startsWith("{") && json.endsWith("}")) {
            return json.substring(1, json.length() - 1);
        }
        return json;
    }
}
