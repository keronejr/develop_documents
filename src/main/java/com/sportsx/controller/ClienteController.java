package com.sportsx.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sportsx.model.Cliente;
import com.sportsx.model.ListaTelefonesWrapper;
import com.sportsx.model.Pesquisa;
import com.sportsx.model.Telefone;
import com.sportsx.service.ClienteService;
import com.sportsx.service.TelefoneService;

@Controller
@RequestMapping("/SportsX")
public class ClienteController {
	
	@Autowired
    private ClienteService clienteService;
	@Autowired
	private TelefoneService telefoneService;
	
	@RequestMapping("")
	public ModelAndView listarClientes(ModelMap model) {
		initializeFileds(model);
		return new ModelAndView("home", model);
	}	
	
	private void initializeFileds(ModelMap model) {
		model.addAttribute("clientes", clienteService.recuperarTodos());
		model.addAttribute("tipoP", "ALL");
		model.addAttribute("pesquisa", new Pesquisa("ALL"));
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("telefone", "");
	}
	
	@RequestMapping("/filtrar/{tipoP}")
    public ModelAndView listarClientesPorTipo(@PathVariable("tipoP") String tipoP, ModelMap model) {
        model.addAttribute("clientes", clienteService.recuperarPorTipo(tipoP));
        model.addAttribute("tipoP", tipoP);
        model.addAttribute("pesquisa", new Pesquisa(tipoP));
        return new ModelAndView("home", model);
    }
	
	@RequestMapping("/cliente/{tipo}/tipo")
	public ModelAndView alterarTipoNovoCliente(@PathVariable("tipo") String tipo, @ModelAttribute("cliente") Cliente cliente, ModelMap model) {
		return new ModelAndView("/cliente/cliente", model);
	}
	
	
	/*@RequestMapping("/cliente/{tipo}/tipo/{id}")
	public ModelAndView alterarTipoCliente(@PathVariable("tipo") String tipo, @PathVariable("id") long id, ModelMap model) {
		Cliente cliente = clienteService.recuperarPorID(id);
		cliente.setTipo(tipo);
		model.addAttribute("cliente", cliente);
		return new ModelAndView("/cliente/cliente", model);
	}*/
	
	@RequestMapping("/cliente/{id}/telefone/{idxTel}/remover")
	public ModelAndView removerTelefone(@PathVariable("id") long id, @PathVariable("idxTel") int idxTel, @ModelAttribute ListaTelefonesWrapper listaTelefonesWrapper, ModelMap model, Model modelF) {
		//listaTelefonesWrapper.getListaTelefones().remove(idxTel);
		model.addAttribute("listaTelefoneWrapper", listaTelefonesWrapper);
		return new ModelAndView("/cliente/cliente", model);
	}

	
	@PostMapping("/pesquisar")
    public ModelAndView listarClientesPorTipoENomePost(@ModelAttribute("pesquisa") Pesquisa pesquisa, ModelMap model) {
		model.addAttribute("clientes", clienteService.recuperarPorTipoENome(pesquisa.getTipo(), pesquisa.getNome()));
		model.addAttribute("tipoP", pesquisa.getTipo());
		model.addAttribute("pesquisa", pesquisa);
        return new ModelAndView("home", model);
    }	
	
	@RequestMapping("/cadastrar")
    public String cadastrarCliente(@ModelAttribute("cliente") Cliente cliente, @ModelAttribute("telefone") String telefone, RedirectAttributes attr, ModelMap model) {
		//Cliente cliente = 
		/*ListaTelefonesWrapper listaTelefoneWrapper = new ListaTelefonesWrapper();
		for (int i = 1; i <= 3; i++) {
			Telefone tel = new Telefone();
			tel.setNumero("999999999");
			listaTelefoneWrapper.addtelefone(tel);
	    }
		model.addAttribute("listaTelefoneWrapper", listaTelefoneWrapper);*/
        return "/cliente/cliente";
    }
	
	@GetMapping("/cliente/{id}/editar")
	public ModelAndView editarCliente(@PathVariable("id") long id, ModelMap model) {
		Cliente cliente = clienteService.recuperarPorID(id);
		model.addAttribute("cliente", cliente);
		if (cliente.getTelefones() != null && cliente.getTelefones().size() > 0) {
			cliente.setTelefone(cliente.getTelefones().get(0).getNumero().toString());
		} else {
			cliente.setTelefone("");
		}
		return new ModelAndView("/cliente/cliente", model);
    }
	
	@GetMapping("/cliente/{id}/excluir")
	public ModelAndView excluirCliente(@PathVariable("id") long id, ModelMap model) {
		clienteService.excluir(id);
		model.addAttribute("mensagem", "Cliente excluído com sucesso!");
		initializeFileds(model);
		return new ModelAndView("home", model);
	}
	
    @PostMapping("/cliente/salvar")
    public String salvarCliente(@Valid @ModelAttribute("cliente") Cliente cliente, Model model, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/cliente/cliente";
        }
        if (cliente.getTelefone() != null && !"".equals(cliente.getTelefone())) {
	        Telefone tel = new Telefone();
	    	tel.setCliente(cliente);
	    	tel.setNumero(cliente.getTelefone());
	    	cliente.getTelefones().add(tel);
        }
        clienteService.salvar(cliente);
        attr.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
        return "redirect:/SportsX/";
    }
    
    @PutMapping("/cliente/salvar")
    public String atualizarCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/cliente/cliente";
        }
        cliente.getTelefones().clear();
        telefoneService.excluirPorClienteID(cliente.getId());
        if (cliente.getTelefone() != null && !"".equals(cliente.getTelefone())) {
        	Telefone tel = new Telefone();
        	tel.setCliente(cliente);
        	tel.setNumero(cliente.getTelefone());
        	cliente.getTelefones().add(tel);
        }
        clienteService.ataulizar(cliente);
        attr.addFlashAttribute("mensagem", "Cliente atualizado com sucesso!");
        return "redirect:/SportsX/";
    }
}


