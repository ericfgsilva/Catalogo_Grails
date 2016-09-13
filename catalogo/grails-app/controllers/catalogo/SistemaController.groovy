package catalogo

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SistemaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Sistema.list(params), model:[sistemaCount: Sistema.count()]
    }

    def show(Sistema sistema) {
        respond sistema
    }

    def create() {
        respond new Sistema(params)
    }

    @Transactional
    def save(Sistema sistema) {
        if (sistema == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sistema.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sistema.errors, view:'create'
            return
        }

        sistema.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sistema.label', default: 'Sistema'), sistema.id])
                redirect sistema
            }
            '*' { respond sistema, [status: CREATED] }
        }
    }

    def edit(Sistema sistema) {
        respond sistema
    }

    @Transactional
    def update(Sistema sistema) {
        if (sistema == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sistema.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sistema.errors, view:'edit'
            return
        }

        sistema.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sistema.label', default: 'Sistema'), sistema.id])
                redirect sistema
            }
            '*'{ respond sistema, [status: OK] }
        }
    }

    @Transactional
    def delete(Sistema sistema) {

        if (sistema == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        sistema.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sistema.label', default: 'Sistema'), sistema.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sistema.label', default: 'Sistema'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
