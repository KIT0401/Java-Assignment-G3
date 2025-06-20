package backend;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class datamanager {
    public static void main(String[] args) {
//        ArrayList<Object> newLine = new ArrayList<>(Arrays.asList("kit8qw123123eq", "kit81123123231"));
//
//        addData("users.txt", newLine);

        ArrayList<Object> newLine = new ArrayList<>(Arrays.asList(1, "kit", "kit"));

        updateData("users.txt", newLine);
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

    public static Path getFileLocation(String filename){
        Path filePath = Paths.get("data/"+ filename);
        System.out.println(filePath.toAbsolutePath());
        return filePath;
    }

    public static int generateID(String filename){
        int id = 0;
        File file_location = getFileLocation(filename).toFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(file_location))) {
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