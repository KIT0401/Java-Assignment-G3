package backend;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class datamanager {
    public static void main(String[] args) {
//        ArrayList<Object> newLine = new ArrayList<>(Arrays.asList("kit8qw123123eq", "kit81123123231"));
//
//        addData("users.txt", newLine);

//        ArrayList<Object> newLine = new ArrayList<>(Arrays.asList(1, "kit", "kit"));
//
//        updateData("users.txt", newLine);

        // deleteData("users.txt","1");

        Object username;

        ArrayList<Object> data = getData("users.txt","1");

        System.out.println(data);

        username = data.get(5);

        System.out.println(username);

    }
    public static void addData(String filename, ArrayList<Object> data) {
        File file_location = getFileLocation(filename).toFile();

        data.addFirst(generateID(filename));

        String string_data = data.subList(0, data.size())
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_location,true))) {
            writer.write(string_data + "\n");
        } catch (IOException e) {
            System.out.println("Error adding file: " + e.getMessage());
        }
    }

    public static void updateData(String filename, ArrayList<Object> data) {
        String string_data = data.subList(0, data.size())
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        ArrayList<String> data_lines = new ArrayList<>();

        File file_location = getFileLocation(filename).toFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(file_location))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.trim().split(",");

                    if (parts[0].equals(data.getFirst().toString())) {
                        data_lines.add(string_data);
                    } else {
                        data_lines.add(line.trim());
                    }

                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_location))) {
            for (String i : data_lines) {
                writer.write(i + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public static void deleteData(String filename, String id){
        File file_location = getFileLocation(filename).toFile();

        ArrayList<String> data_lines = new ArrayList<>();
        String first_line;

        try (BufferedReader reader = new BufferedReader(new FileReader(file_location))) {
            String line;

            first_line = reader.readLine();
            reader.readLine();

            String[] first_line_split = first_line.trim().split(",");

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.trim().split(",");

                    if (parts[0].equals(id)) {
                        int active_index = 0;

                        for (int i = 0; i < first_line_split.length; i++) {
                            if (first_line_split[i].equalsIgnoreCase("active")) {
                                active_index = i;
                                break;
                            }
                        }

                        if (active_index != 0) {
                            parts[active_index] = String.valueOf(false);
                            data_lines.add(String.join(",", parts));
                        }
                    } else {
                        data_lines.add(line.trim());
                    }

                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println(data_lines);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_location))) {
            writer.write(first_line + "\n");

            for (String i : data_lines) {
                writer.write(i + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    };

    public static ArrayList<Object> getData(String filename, String id){
        ArrayList<Object> data = new ArrayList<>();

        File file_location = getFileLocation(filename).toFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(file_location))) {
            String line;

            reader.readLine();
            String[] type_line = reader.readLine().trim().split(",");

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.trim().split(",");

                    if (parts[0].equals(id)) {
                        for (int i = 0; i < type_line.length; i++) {
                            String type = type_line[i];

                            if (type.equalsIgnoreCase("string")) {
                                if (parts[i].equalsIgnoreCase("null")) {
                                    data.add(i,null);

                                } else {
                                    data.add(i,parts[i]);
                                }
                            } else if (type.equalsIgnoreCase("boolean")) {
                                data.add(i,Boolean.parseBoolean(parts[i]));
                            } else if (type.equalsIgnoreCase("int")) {
                                data.add(i,Integer.parseInt(parts[i]));
                            } else if (type.equalsIgnoreCase("double")) {
                                data.add(i,Double.parseDouble(parts[i]));
                            } else {
                                System.out.println("Unknown Data Type " + type);
                            }
                        }

                    }

                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return data;
    }

    public boolean login(String username, String password){
        try (BufferedReader reader = new BufferedReader(new FileReader(getFileLocation("users.txt").toFile()))) {
            String line;

            reader.readLine();
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.trim().split(",");

                    // System.out.println(parts[1] + " " + username + " | " + parts[2] + " " + password);

                    if (parts[1].equalsIgnoreCase(username) && parts[2].equals(password)) {
                        if (parts[3].equalsIgnoreCase("false")) {
                            return false;
                        }

                        ArrayList<Object> data = getData("users.txt",parts[0]);
                        // System.out.println(data.get(4));

                        if (data.get(4).equals("admin")) {
                            System.out.println("Admin Log In");
                            admin ADMIN = new admin(
                                    parts[0],
                                    parts[1],
                                    parts[2],
                                    parts[5],
                                    parts[6],
                                    parts[7],
                                    parts[8]
                            );

                        } else if (data.get(4).equals("receptionist")) {
                            System.out.println("Receptionist Log In");
                            receptionist RECEPTIONIST = new receptionist(
                                    parts[0],
                                    parts[1],
                                    parts[2],
                                    parts[5],
                                    parts[6],
                                    parts[7],
                                    parts[8]
                            );

                        } else if (data.get(4).equals("tutor")) {
                            System.out.println("Tutor Log In");

                            tutor TUTOR = new tutor(
                                    parts[0],
                                    parts[1],
                                    parts[2],
                                    parts[5],
                                    parts[6],
                                    parts[7],
                                    parts[8]
                            );

                        } else if (data.get(4).equals("student")) {
                            System.out.println("Student Log In");

                            student STUDENT = new student(
                                    parts[0],
                                    parts[1],
                                    parts[2],
                                    parts[5],
                                    parts[6],
                                    parts[7],
                                    parts[8]
                            );

                        }
                        return true;
                    }

                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return false;
    }

    public void logout() {

    }

    public static Path getFileLocation(String filename){
        Path filePath = Paths.get("data/"+ filename);
        // System.out.println(filePath.toAbsolutePath());
        return filePath;
    }

    public static int generateID(String filename){
        int id = 0;
        File file_location = getFileLocation(filename).toFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(file_location))) {
            reader.readLine();
            reader.readLine();

            while ((reader.readLine()) != null) {
                id ++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        id ++;

        return id;
    }
}