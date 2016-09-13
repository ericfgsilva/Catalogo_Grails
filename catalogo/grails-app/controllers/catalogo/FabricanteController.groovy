package catalogo

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FabricanteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Fabricante.list(params), model:[fabricanteCount: Fabricante.count()]
    }

    def show(Fabricante fabricante) {
        respond fabricante
    }

    def create() {
        respond new Fabricante(params)
    }

    @Transactional
    def save(Fabricante fabricante) {
        if (fabricante == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (fabricante.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond fabricante.errors, view:'create'
            return
        }

        fabricante.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fabricante.label', default: 'Fabricante'), fabricante.id])
                redirect fabricante
            }
            '*' { respond fabricante, [status: CREATED] }
        }
    }

    def edit(Fabricante fabricante) {
        respond fabricante
    }

    @Transactional
    def update(Fabricante fabricante) {
        if (fabricante == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (fabricante.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond fabricante.errors, view:'edit'
            return
        }

        fabricante.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'fabricante.label', default: 'Fabricante'), fabricante.id])
                redirect fabricante
            }
            '*'{ respond fabricante, [status: OK] }
        }
    }

    @Transactional
    def delete(Fabricante fabricante) {

        if (fabricante == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        fabricante.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'fabricante.label', default: 'Fabricante'), fabricante.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fabricante.label', default: 'Fabricante'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
