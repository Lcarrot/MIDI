import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player {

    private MidiChannel[] channels;
    private Synthesizer synthesizer;

    public Player() {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channels = synthesizer.getChannels();
            channels[0].programChange(25);

        } catch (MidiUnavailableException e) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void close() {
        synthesizer.close();
    }

    public void playNote(int a) {
        try {
            int note = a - (int) 'a' + 60;
            channels[0].noteOn(note, 80);
            Thread.sleep(1000);
            channels[0].noteOff(note);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void writeNotes(String path, String notes) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(notes);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("This file doesn't exist.");
        }
    }

    public void playFromFile(String path) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String notesLine;
            while ((notesLine = reader.readLine()) != null) {
                for (int i = 0; i < notesLine.length(); i++) playNote(notesLine.charAt(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("This file doesn't exist!");
        } catch (IOException e) {
            System.out.println("Problem with stream");
        }
    }
}
