
package BancoDeDados;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistenciaDeDados {
    
    public static void Create(UsuarioDto data){
        
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Save.wah"));
            out.writeObject(data);
            System.out.println("Object saved successfully!");
        } catch (Exception e) {
            System.out.println(e + "null return");
        }
    }
    public static UsuarioDto Read(){
    
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Save.wah"))) {
            UsuarioDto p = (UsuarioDto) in.readObject();
            System.out.println("Loaded: " + p.getName() + ", " + p.getPontuacao());
            return p;
        } catch (Exception e) {
            System.out.println("null return");
            return null;
        }
    }
}
