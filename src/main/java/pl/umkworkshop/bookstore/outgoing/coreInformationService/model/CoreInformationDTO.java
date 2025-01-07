package pl.umkworkshop.bookstore.outgoing.coreInformationService.model;

public record CoreInformationDTO(Long id,
                                 String title,
                                 String authorFirstName,
                                 String authorLastName) {
}
