package com.dut.kidoi.repositories;

import android.telecom.Call;
import android.util.Log;

import com.dut.kidoi.models.User;
import com.dut.kidoi.utils.Callback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class FirebaseRepository {

    private static FirebaseRepository instance;
    private FirebaseFirestore db;
    private User connectedUser;

    static {
        instance = new FirebaseRepository();
    }

    public FirebaseRepository() {
        db = FirebaseFirestore.getInstance();
    }

    public static FirebaseRepository getInstance() {
        return instance;
    }

    public void addUser(String email, String username, String uID, Callback<User> cb) {
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("username", username);
        user.put("userID", uID);

        db.collection("users")
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    Log.d("", "DocumentSnapshot added with ID: " + documentReference.getId());
                    if(cb != null)
                        getUser(uID, cb);
                })
                .addOnFailureListener(e -> {
                    Log.w("", "Error adding document", e);
                    if(cb != null)
                        cb.call(null);
                });
    }

    public void updateUser(User user) {
        db.collection("users").document(user.getDocumentId())
                .update("email", user.getEmail(), "username", user.getLogin(), "userID", user.getDocumentId());
    }

    public void getUser(String uuid, Callback<User> cb) {
        db.collection("users").whereEqualTo("userID", uuid).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot document = task.getResult();

                if (document.getDocuments().isEmpty()) {
                    cb.call(null);
                    return;
                }

                cb.call(new User(
                        document.getDocuments().get(0).getString("username"),
                        document.getDocuments().get(0).getString("email"),
                        document.getDocuments().get(0).getString("userID")
                ));
            }
        });
    }

    public void getUser(Callback<User> cb){
        getUser(FirebaseAuth.getInstance().getCurrentUser().getUid(), user -> {
            connectedUser = user;
            cb.call(connectedUser);
        });
    }

    public User getConnectedUser() {
        return connectedUser;
    }

    public void reset(){
        connectedUser = null;
    }
}
