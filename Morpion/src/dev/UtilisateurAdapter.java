package dev;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class UtilisateurAdapter extends TypeAdapter<Utilisateur> {
    @Override
    public void write(JsonWriter out, Utilisateur utilisateur) throws IOException {
        out.beginObject();
        out.name("nomUtilisateur").value(utilisateur.getNomUtilisateur());
        out.name("motDePasse").value(utilisateur.getMotDePasse());
        out.endObject();
    }

    @Override
    public Utilisateur read(JsonReader in) throws IOException {
        in.beginObject();
        String nomUtilisateur = null;
        String motDePasse = null;
        while (in.hasNext()) {
            String key = in.nextName();
            if ("nomUtilisateur".equals(key)) {
                nomUtilisateur = in.nextString();
            } else if ("motDePasse".equals(key)) {
                motDePasse = in.nextString();
            }
        }
        in.endObject();
        return new Utilisateur(nomUtilisateur, motDePasse);
    }
}

