package com.kodlamaio.invoiceservice.repository;

import com.kodlamaio.invoiceservice.entities.Invoice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


public interface InvoiceRepository extends ElasticsearchRepository<Invoice,String> {
  List<Invoice> findAll();
}
