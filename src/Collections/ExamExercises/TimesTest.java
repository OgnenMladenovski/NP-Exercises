//Zadacha 7/51
package Collections.ExamExercises;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class UnsupportedFormatException extends Exception {
    public UnsupportedFormatException(String message) {
        super(message);
    }
}

class InvalidTimeException extends Exception {
    public InvalidTimeException(String message) {
        super(message);
    }
}

class Time {
    private int hour;
    private int minutes;

    public Time(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }
}

class TimeTable {
    private List<Time> times = new ArrayList<>();

    public TimeTable() {
    }

    public void readTimes(InputStream inputStream) throws IOException, UnsupportedFormatException, InvalidTimeException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }

            String[] parts = line.trim().split("\\s+");

            for (String part : parts) {
                if (part.contains(":")) {
                    String[] partsWithTwoDots = part.split(":");
                    int hour = Integer.parseInt(partsWithTwoDots[0]);
                    int minutes = Integer.parseInt(partsWithTwoDots[1]);
                    if(hour < 0 || hour > 23)
                    {
                        throw new InvalidTimeException("NEdobro vreme");
                    }
                    else if(minutes < 0 || minutes > 59)
                    {
                        throw new InvalidTimeException("NEdobro vreme");
                    }
                    else
                        times.add(new Time(hour, minutes));
                } else if (part.contains(".")) {
                    String[] partsWithOneDot = part.split("\\.");
                    int hour = Integer.parseInt(partsWithOneDot[0]);
                    int minutes = Integer.parseInt(partsWithOneDot[1]);
                    if(hour < 0 || hour > 23)
                    {
                        throw new InvalidTimeException("NEdobro vreme");
                    }
                    else if(minutes < 0 || minutes > 59)
                    {
                        throw new InvalidTimeException("NEdobro vreme");
                    }
                    else
                        times.add(new Time(hour, minutes));
                }
                else
                    throw new UnsupportedFormatException(part);
            }

        }
    }

    public void writeTimes(OutputStream outputStream, TimeFormat format) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        times.sort(Comparator.comparing(Time::getHour).thenComparing(Time::getMinutes));
        if (format == TimeFormat.FORMAT_24) {
            for (Time time : times) {
                bw.append(String.format("%2d:%02d\n", time.getHour(), time.getMinutes()));
            }
        } else if (format == TimeFormat.FORMAT_AMPM) {
            for (Time time : times) {
                if (time.getHour() == 0) {
                    int newHour = 12;
                    bw.append(String.format("%2d:%02d AM\n", newHour, time.getMinutes()));
                }
                else if (time.getHour() > 12) {
                    int newHour = time.getHour() - 12;
                    bw.append(String.format("%2d:%02d PM\n", newHour, time.getMinutes()));
                }
                else if (time.getHour() == 12) {
                    bw.append(String.format("%2d:%02d PM\n", time.getHour(), time.getMinutes()));
                }
                else if (time.getHour() < 12) {
                    if(time.getHour() == 1)
                    {
                        bw.append(String.format("%2d:%02d AM\n", time.getHour(), time.getMinutes()));
                    }
                    else if (time.getHour() < 10) {
                        bw.append(String.format("%2d:%02d PM\n", time.getHour(), time.getMinutes()));
                    }
                    else
                        bw.append(String.format("%2d:%02d AM\n", time.getHour(), time.getMinutes()));
                }
            }
        }
        bw.flush();
    }
}

public class TimesTest {

    public static void main(String[] args) throws IOException {
        TimeTable timeTable = new TimeTable();
        try {
            timeTable.readTimes(System.in);
        } catch (UnsupportedFormatException e) {
            System.out.println("UnsupportedFormatException: " + e.getMessage());
        } catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException: " + e.getMessage());
        }
        System.out.println("24 HOUR FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_24);
        System.out.println("AM/PM FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_AMPM);
    }

}

enum TimeFormat {
    FORMAT_24, FORMAT_AMPM
}
