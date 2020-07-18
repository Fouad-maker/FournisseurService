package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Article;
import com.example.demo.entity.ArticleVenteFlash;
import com.example.demo.entity.Categorie;

import com.example.demo.entity.Fournisseur;
import com.example.demo.entity.Marque;
import com.example.demo.entity.SousCategorie;
import com.example.demo.entity.VenteFlash;
import com.example.demo.service.FournisseurService;
import com.example.demo.service.RestAdminService;




@RestController
public class FournisseurController {
	
		@Autowired
		private FournisseurService fournisseurService ;
		
		@Autowired
		private RestAdminService restAdminService;
		
		
		
		
		
		/* Gestion des Articles  Ajout + Update + Delete + Affichage */
		
		@PostMapping("/article")
		public Article addArticle (@RequestBody Article article) {
			Article art = new Article();
			art.setId(article.getId());
			art.setMat(article.getMat());
			art.setDesigntationArt(article.getDesigntationArt());
			art.setDescriptionArt(article.getDescriptionArt());
			art.setImageModel(article.getImageModel());
			art.setPrixArt(article.getPrixArt());
			art.setQteStockArt(article.getQteStockArt());
			art.setTauxRemiseArt(article.getTauxRemiseArt());
			
			Marque m = fournisseurService.findMarqueById(article.getMarqueArt().getId());
			art.setMarqueArt(m);
			SousCategorie sousCat = fournisseurService.findSousCategorieById(article.getSousCategorieArt().getId());
			art.setSousCategorieArt(sousCat);
			Fournisseur f = fournisseurService.findFournisseurById(article.getFournisseurArt().getUsername());
			art.setFournisseurArt(f);
			fournisseurService.addArticle(art);
			return art ;
			
			
		}
		
		

		 @GetMapping("/article")
		public CollectionModel<Article>  listArticles()
		{
			return fournisseurService.findAllArticle();
		} 

		@GetMapping("/article/{id}")
		public  Article getArticle(@PathVariable ("id") String id )
		{
			return fournisseurService.getArticleById(id);
		}
		
		
		@PutMapping("/article/{id}")
		public Article modifyArticle(@RequestBody Article article , @PathVariable("id") String id) {
			
					Article art = new Article();
					art.setMat(article.getMat());
					art.setDesigntationArt(article.getDesigntationArt());
					art.setDescriptionArt(article.getDescriptionArt());
					art.setImageModel(article.getImageModel());
					art.setPrixArt(article.getPrixArt());
					art.setQteStockArt(article.getQteStockArt());
					art.setTauxRemiseArt(article.getTauxRemiseArt());
					Marque m = fournisseurService.findMarqueById(article.getMarqueArt().getId());
					art.setMarqueArt(m);
					SousCategorie sousCat = fournisseurService.findSousCategorieById(article.getSousCategorieArt().getId());
					art.setSousCategorieArt(sousCat);
					Fournisseur f = fournisseurService.findFournisseurById(article.getFournisseurArt().getUsername());
					art.setFournisseurArt(f);
					
					
						
			fournisseurService.updatArticle(art,id);
			return art;	
		}
		
		@DeleteMapping ("article/{id}")
		public void deleteArticle (@PathVariable("id") String id)
		{
			fournisseurService.deleteArticle(id);	
		}
		
		/* Ajout Marque + list Marque */
		
		@PostMapping("/marque")
		public Marque addMarque (@RequestBody Marque marque )
		{
			Marque m = new Marque(); 
			m.setId(marque.getId());
			m.setMat(marque.getMat());
			m.setLibelleMarq(marque.getLibelleMarq());
			
			fournisseurService.addMarque(m);
			return m ; 
		}
		@GetMapping ("/marque")
		public CollectionModel<Marque> listMarque ()
		{
			return fournisseurService.findAllMarque();
		}
		
		@GetMapping ("/marque/{id}")
		public  Marque getMarque (@PathVariable ("id") String id )
		{
			return fournisseurService.findMarqueById(id);
		}
		
		@PutMapping("/marque/{id}")
		public Marque modifyMarque  (@RequestBody Marque marque, @PathVariable ("id")  String id)
		{
			Marque m = new Marque(); 
			m.setLibelleMarq(marque.getLibelleMarq());
			fournisseurService.updateMarque(m, id);
			return m ;
		}
		
	
		
		
		/* Afiichage + Ajout Sous_Cathegorie */
		
		@PostMapping("/souscategorie")
		public SousCategorie addSousCategorie (@RequestBody SousCategorie sousCategorie )
		{
			SousCategorie sousCat = new SousCategorie(); 
			sousCat.setId(sousCategorie.getId());
			sousCat.setMat(sousCategorie.getMat());
			sousCat.setLibelleSousCat(sousCategorie.getLibelleSousCat());
			sousCat.setValeur(sousCategorie.getValeur());
			Categorie cat = fournisseurService.findCategorieById(sousCategorie.getCategorie().getId());
			if (cat != null )
			{
				System.err.println(cat);
				sousCat.setCategorie(cat);
			}
	        
			fournisseurService.addSousCategorie(sousCat);
			return sousCat ; 
			
		}
		
		@GetMapping ("/souscategorie")
		public CollectionModel<SousCategorie> listSousCategorie ()
		{
			return fournisseurService.findAllSousCategorie();
		}
		
		@GetMapping ("/souscategorie/{id}")
		public SousCategorie getSousCategorie (@PathVariable ("id") String id )
		{
			return fournisseurService.findSousCategorieById(id);
		}
		
		
		
		/* Ajout + Affichage Categorie */ 
		
		@PostMapping("/categorie")
		public Categorie addCategorie (@RequestBody Categorie categorie )
		{
			Categorie cat = new Categorie(); 
			cat.setId(categorie.getId());
			cat.setMat(categorie.getMat());
			cat.setLibelleCat(categorie.getLibelleCat());
			
			fournisseurService.addCategorie(cat);
			return cat; 
		}
		
	
		@GetMapping ("/categorie")
		public CollectionModel<Categorie> listCategorie ()
		{
			return fournisseurService.findAllCategorie();
		}
		
		@GetMapping ("/categorie/{id}")
		public  Categorie getCategorie (@PathVariable ("id") String id )
		{
			return fournisseurService.findCategorieById(id);
		}
				
		
		/* Modifier Porfil Fournisseur */
		
		
		@GetMapping ("/fournisseur/{id}")
		public Fournisseur findFournisseurById (@PathVariable ("id") String id )
		{
			return fournisseurService.findFournisseurById(id);
		}
		
		/*@PutMapping ("/fournisseur/{username}")
		public Fournisseur updateFournisseur (@RequestBody Fournisseur fournisseur , @PathVariable ("id") String id )
		{
			Fournisseur four = new Fournisseur();
			four.setNom(fournisseur.getNom());
			four.setAdresse(fournisseur.getAdresse());
			four.setUsername(fournisseur.getUsername());
			four.setPassword(fournisseur.getPassword());
			four.setPrenom(fournisseur.getPrenom());
			four.setPhoneNumber(fournisseur.getPhoneNumber());
			
			fournisseurService.updateFournisseur(four, username);
			return four ; 
		}*/
		
		/*    Vente Flash      */
		
		/*@PostMapping("/venteflash")
		public VenteFlash postVenteFlash (@RequestBody VenteFlash venteFlash )
		{
			VenteFlash v = fournisseurService.findVenteFlashById(venteFlash.getMat());
			System.out.println(v.getDateFinVF());
			System.out.println(v.getDateDebVF());
			VenteFlash v1 = new VenteFlash();
			v1.setMat(venteFlash.getMat());
			v1.setId(venteFlash.getId());
			v1.setDateDebVF(v.getDateDebVF());
			v1.setDateFinVF(v.getDateFinVF());
			
			List<ArticleVenteFlash> a = new ArrayList<>();
			List<ArticleVenteFlash> c= new ArrayList<>();
			

			
			c=v.getArticleVenteFlash();	

			a.addAll(venteFlash.getArticleVenteFlash());
			c.addAll(a);
			
			System.out.println(c);
			v1.setArticleVenteFlash(c);
			System.out.println(v1);
			fournisseurService.addArticleventeflash(v1);
			return v1 ; 
			
		}*/
		
		
		@GetMapping ("/venteflash/{id}")
		public VenteFlash findVenteFlashById (@PathVariable ("id") String id )
		{
			return fournisseurService.findVenteFlashById(id);
		}
		
		
		@GetMapping ("/venteflash")
		public CollectionModel<VenteFlash> listVenteFlash ()
		{
			return fournisseurService.findAllVenteFlash();
		}
		
		
		@PostMapping("/venteflash")
        public VenteFlash postVenteFlash (@RequestBody VenteFlash venteFlash )
        {
            VenteFlash v = fournisseurService.findVenteFlashById(venteFlash.getId());
            System.out.println(v.getDateFinVF());
            System.out.println(v.getDateDebVF());
            VenteFlash v1 = new VenteFlash();
            v1.setMat(venteFlash.getMat());
            v1.setId(venteFlash.getId());
            v1.setDateDebVF(v.getDateDebVF());
            v1.setDateFinVF(v.getDateFinVF());
           
            List<ArticleVenteFlash> a = new ArrayList<>();
            List<ArticleVenteFlash> c= new ArrayList<>();
           
           
            c=v.getArticleVenteFlash();
           
            System.err.println(c);
            a.addAll(venteFlash.getArticleVenteFlash());
            System.err.println(a);
            if (c== null )
            {
                v1.setArticleVenteFlash(a);
            }else {
                c.addAll(a);     
                v1.setArticleVenteFlash(c);
            }
           
            fournisseurService.addArticleventeflash(v1);
            return v1 ;
           
        }
		
		
		/*@PostMapping("/articleventeflash")
		public ArticleVenteFlash postArticleVenteFlash (@RequestBody ArticleVenteFlash article) {
			ArticleVenteFlash art = new ArticleVenteFlash();
			art.setId(article.getId());
			art.setMat(article.getMat());
			art.setDesigntationArt(article.getDesigntationArt());
			art.setDescriptionArt(article.getDescriptionArt());
			art.setImageModel(article.getImageModel());
			art.setPrixArt(article.getPrixArt());
			art.setQteStockArt(article.getQteStockArt());
			art.setTauxRemiseArt(article.getTauxRemiseArt());
			
			Marque m = fournisseurService.findMarqueById(article.getMarqueArt().getId());
			art.setMarqueArt(m);
			SousCategorie sousCat = fournisseurService.findSousCategorieById(article.getSousCategorieArt().getId());
			art.setSousCategorieArt(sousCat);
			Fournisseur f = fournisseurService.findFournisseurById(article.getFournisseurArt().getUsername());
			art.setFournisseurArt(f);
			art.setPrixArtVF(article.getPrixArtVF());
			art.setQteStockArtVF(article.getQteStockArtVF());
			
			
			fournisseurService.addArticleventeflash(art);
			return art ;
			
			
		}*/
		
		
		
		
		
		
		
		
		
		
		/*@PostMapping("/addetatventeflash")
		 EtatArticleVenteFlash addEtatArticleVenteFlash (@RequestBody EtatArticleVenteFlash etat)
		
			EtatArticleVenteFlash e = new EtatArticleVenteFlash();
			e.setIdAVF(etat.getIdAVF());
			e.setPrixAVF(etat.getPrixAVF());
			e.setQteAVF(etat.getQteAVF());
			Article a = fournisseurService.getArticleById(e.getArticleAVF().getIdArt());
			e.setArticleAVF(a);
			a.setQteStockArt((a.getQteStockArt())-(e.getQteAVF()));
					
			fournisseurService.addEtatArticleVenteFlash(e);
			return e ; 
		}
		
		public void addEtatToVenteFlash ( String idVF , String idEVF ) {
			
			VenteFlash v  = fournisseurService.findVenteFlashById(idVF);
			EtatArticleVenteFlash etat = fournisseurService.findEtatArticleVenteFlashById(idEVF);
			v.getEtatArticleVenteFlashs().add(etat);	
		
		}
		
	        
		
		@PostMapping("/addventeflash")
		public VenteFlash addVenteFlash(@RequestBody VenteFlash venteFlash , @RequestBody String idEtat)
		{
			EtatArticleVenteFlash etat = fournisseurService.findEtatArticleVenteFlash(idEtat); 
			VenteFlash v = new VenteFlash();
			v.setIdVF(venteFlash.getIdVF());
			v.setDateDebVF(venteFlash.getDateDebVF());
			v.setDateFinVF(venteFlash.getDateFinVF());
			//addEtatToVenteFlash(v.getIdVF(), etat.getIdAVF());
			
			fournisseurService.addVenteFlash(v);
			 return v;
		}
		
		
		
		@Scheduled(cron = "1 * * * * *")
		@DeleteMapping("/deleteventeflash/{id}")
		public void  deleteVenteFlash (@PathVariable ("id") String id )
		{
			LocalDateTime date = LocalDateTime.now();
			VenteFlash v = fournisseurService.findVenteFlashById(id);
			if (date.isAfter(v.getDateFinVF()))
			{
				fournisseurService.deleteVenteFlash(id);
			}
		}*/
		
	
	
       
		
		
		
		}
		

		


