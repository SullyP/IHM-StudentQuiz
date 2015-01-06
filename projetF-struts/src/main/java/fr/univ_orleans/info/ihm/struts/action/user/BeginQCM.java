package fr.univ_orleans.info.ihm.struts.action.user;

import fr.univ_orleans.info.ihm.modele.beans.IQuestion;
import fr.univ_orleans.info.ihm.modele.beans.IReponse;
import fr.univ_orleans.info.ihm.modele.beans.IResultatUtilisateur;
import fr.univ_orleans.info.ihm.struts.action.def.ServiceAndSessionAwareAction;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ParentPackage(value = "user")
@Namespace(value = "/user")
public class BeginQCM extends ServiceAndSessionAwareAction{
    private static final Logger LOGGER = Logger.getLogger(BeginQCM.class.getCanonicalName());
    private int idQCM=0;
    private int idQuestion;
    private int dureeQuestion;
    private int pointQuestion;
    private boolean multipleQuestion;
    private String intituleQuestion;
    private List<IReponse> reponses;

    @Action(value = "beginQCM", results = {
            @Result(type = "tiles", location = "user/question.tiles")
    })
    @Override
    public String execute() {
        try {
            int idUtilisateur = (int)this.getSession().get("userId");
            Date date = Calendar.getInstance().getTime();
            IResultatUtilisateur resultatUtilisateur = this.getModeleService().creerResultatUtilisateur(idUtilisateur,idQCM, date);
            this.getSession().put("idResultatUtilisateur",resultatUtilisateur.getIdResultatUtilisateur());
            IQuestion question = this.getModeleService().getNextQuestionQCM(idQCM,resultatUtilisateur.getIdResultatUtilisateur());
            this.idQuestion = question.getIdQuestion();
            this.pointQuestion = question.getPointQuestion();
            this.multipleQuestion = question.isMultipleQuestion();
            this.intituleQuestion = question.getIntituleQuestion();
            this.reponses = question.getReponses();
            this.dureeQuestion = question.getDureeQuestion();
        } catch (RemoteException e) {
            LOGGER.fatal(e);
        }
        return SUCCESS;
    }

    public int getIdQCM() {
        return idQCM;
    }

    public void setIdQCM(int idQCM) {
        this.idQCM = idQCM;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public int getDureeQuestion() {
        return dureeQuestion;
    }

    public int getPointQuestion() {
        return pointQuestion;
    }

    public boolean isMultipleQuestion() {
        return multipleQuestion;
    }

    public String getIntituleQuestion() {
        return intituleQuestion;
    }

    public List<IReponse> getReponses() {
        return reponses;
    }
}
